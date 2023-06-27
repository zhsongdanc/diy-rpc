package com.demus.server;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:49
 */
public interface ServiceRegistry {

    public void registerService(String serviceName, String url);

    public String getService(String serviceName);

}
