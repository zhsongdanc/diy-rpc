package com.demus.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:48
 */
public class SocketServer extends RpcServer{


    private int port;
    private ServiceRegistry serviceRegistry;

    public SocketServer(int port) throws IOException {
        this.port = port;
//        this.serviceProvider = new ServiceProvider();
//        this.serviceRegistry = new ServiceRegistry();

    }

    @Override
    protected void start() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {

            }
        }
    }


}
