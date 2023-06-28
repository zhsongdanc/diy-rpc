package com.demus.server;


import com.demus.common.serialize.Serializer;
import com.demus.server.register.ServiceRegistry;
import com.demus.server.thread.RequestHandler;
import com.demus.server.thread.SocketRequestHandler;
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
    private RequestHandler requestHandler;
    private Serializer serializer;

    public SocketServer(int port) throws IOException {
        this.port = port;

        scanService();
//        this.serviceProvider = new ServiceProvider();
//        this.serviceRegistry = new ServiceRegistry();

    }

    @Override
    protected void start() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                SocketRequestHandler socketRequestHandler = new SocketRequestHandler(requestHandler, socket, serializer);
                new Thread(socketRequestHandler).start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
