package com.demus.server.thread;

import com.alibaba.nacos.spring.util.NacosUtils;
import com.demus.server.register.NacosUtil;
import org.omg.SendingContext.RunTime;

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
