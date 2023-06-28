package com.demus.server.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/28 22:36
 */
@Slf4j
// TODO  daemon thread ???
public class ThreadPoolFactory {

    private static final int CORE_POOL_SIZE = 10;
    private static final int MAXIMUM_POOL_SIZE_SIZE = 100;
    private static final int KEEP_ALIVE_TIME = 1;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;

    private static Map<String, ExecutorService> threadPoolMap = new ConcurrentHashMap<>();


    public static ExecutorService createDefaultThreadPool(String threadNamePrefix) {
        return createDefaultThreadPool(threadNamePrefix, false);
    }

    public static ExecutorService createDefaultThreadPool(String threadNamePrefix, boolean daemon) {
        ExecutorService executor = threadPoolMap.computeIfAbsent(threadNamePrefix, k -> createThreadPool(threadNamePrefix, daemon));
        if (executor.isShutdown() || executor.isTerminated()) {
            threadPoolMap.remove(threadNamePrefix);
            executor = createThreadPool(threadNamePrefix, daemon);
            threadPoolMap.put(threadNamePrefix, executor);
        }
        return executor;
    }

    private static ExecutorService createThreadPool(String threadNamePrefix, boolean daemon) {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY), createThreadFactory(threadNamePrefix, daemon));
    }


    private static ThreadFactory createThreadFactory(String threadNamePrefix, boolean daemon) {
        if (threadNamePrefix != null) {
            return new ThreadFactoryBuilder().setNameFormat(threadNamePrefix + "-%d").setDaemon(daemon).build();
        }
        return Executors.defaultThreadFactory();
    }


    public static void shutdownAll(){
        threadPoolMap.entrySet().parallelStream().forEach( entry -> {
                    ExecutorService executorService = entry.getValue();
                    executorService.shutdown();
                    log.info("close all thread pool:{}", entry.getKey());
                    try {
                        executorService.awaitTermination(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        log.error("close thread pool:{} failed", entry.getKey());
                        executorService.shutdownNow();
                    }
                }

        );
    }
}
