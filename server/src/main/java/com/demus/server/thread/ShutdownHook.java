package com.demus.server.thread;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/28 22:37
 */
public class ShutdownHook {

    public void addClearAllHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            NacosUtil.clearReigstry();
            ThreadPoolFactory.shutdownAll();
        }));
    }
}
