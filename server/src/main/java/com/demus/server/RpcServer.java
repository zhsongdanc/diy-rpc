package com.demus.server;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:47
 */
public abstract class RpcServer {
    protected ServiceProvider serviceProvider;
    protected ServiceRegistry serviceRegistry;


    protected abstract void start() throws RuntimeException;
}
