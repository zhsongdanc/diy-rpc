package com.demus.server.thread;

import com.demus.common.catalina.RpcRequest;
import com.demus.common.catalina.RpcResponse;
import com.demus.common.serialize.ObjectReader;
import com.demus.common.serialize.Serializer;
import com.demus.server.provider.ServiceProvider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 12:08
 */
@Slf4j
public class SocketRequestHandler implements Runnable{

    private RequestHandler requestHandler;
    private Socket socket;
    private Serializer serializer;

    public SocketRequestHandler(RequestHandler requestHandler, Socket socket,
            Serializer serializer) {
        this.requestHandler = requestHandler;
        this.socket = socket;
        this.serializer = serializer;
    }

    @Override
    public void run() {
        try(InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream()) {
            RpcRequest request = ObjectReader.read(inputStream);
            RpcResponse<Object> response = this.requestHandler.handle(request);
            ObjectReader.write(outputStream, response);


        }catch (IOException e) {
            log.error("failed to handle request!");
            e.printStackTrace();
        }
    }
}
