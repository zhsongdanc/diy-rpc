package com.demus.server.socket;


import com.demus.common.serialize.Serializer;
import com.demus.server.provider.ServiceProviderImpl;
import com.demus.server.register.NacosServiceRegistry;
import com.demus.server.register.ServiceRegistry;
import com.demus.server.thread.RequestHandler;
import com.demus.server.thread.SocketRequestHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:48
 */
@Slf4j
public class SocketServer extends RpcServer{


    private int port;
    private RequestHandler requestHandler = new RequestHandler();
    private Serializer serializer;

    public SocketServer(int port) throws IOException {
        this.port = port;
        this.serviceProvider = new ServiceProviderImpl();
        this.serviceRegistry = new NacosServiceRegistry();

        scanService();
//        this.serviceProvider = new ServiceProvider();
//        this.serviceRegistry = new ServiceRegistry();

    }

    @Override
    protected void start() throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                log.info("client connected，ip is :{}", socket.getInetAddress());
                SocketRequestHandler socketRequestHandler = new SocketRequestHandler(requestHandler, socket, serializer);
                new Thread(socketRequestHandler).start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
