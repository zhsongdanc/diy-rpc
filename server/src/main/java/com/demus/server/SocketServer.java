package com.demus.server;


import java.net.ServerSocket;
import java.net.Socket;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:48
 */
public class SocketServer extends RpcServer{

    private ServerSocket serverSocket;

    public SocketServer(int port) {
        this.serviceProvider = new ServiceProvider();
        this.serviceRegistry = new ServiceRegistry();

    }

    @Override
    protected void start() throws RuntimeException {

    }
}
