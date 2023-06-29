package com.demus.common.register;

import java.net.InetSocketAddress;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:49
 */
public interface ServiceRegistry {

    public void registerService(String serviceName, String ip, int port);

    public InetSocketAddress getService(String serviceName);

}
