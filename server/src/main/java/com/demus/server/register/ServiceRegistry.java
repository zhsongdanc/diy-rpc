package com.demus.server.register;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:49
 */
public interface ServiceRegistry {

    public void registerService(String serviceName, String ip, int port);

    public String getService(String serviceName);

}
