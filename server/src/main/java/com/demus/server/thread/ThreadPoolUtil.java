package com.demus.server.thread;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/26 19:43
 */
public class ThreadPoolUtil {

    public static int coreThread = 8;
    public static int maxThread = 8;
    public static long keepAliveTime = 60;
    public static int queueCapacity = 20;

    public static Executor getProcessRequestThreadPool() {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                coreThread,
                maxThread,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity)

        );
        return threadPoolExecutor;

    }

}



