package com.demus.server;

import com.demus.common.catalina.Request;
import com.demus.common.serialize.protostuff.ProtostuffUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.demus.common.catalina.Response;
import java.nio.charset.StandardCharsets;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 13:36
 */

@Slf4j
public class ProcessRequestTask implements Runnable{

    private Socket socket;
    public ProcessRequestTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        while (true) {

            Request request = null;
            try {
                request = deserializeRequest(socket.getInputStream());
                log.info("read content from client: {}", request.getRMethod().getMethodName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            processRequest(request);
//            sendResponse("", socket.getOutputStream());

            // 关闭连接
            try {
                log.info("client:{} disconnected", socket.getInetAddress().getHostAddress());
                socket.getInputStream().close();
                break;
            } catch (IOException e) {
                log.error("server close connection error");
                e.printStackTrace();
            }

        }


    }

    private Response getResponse(String str) {
        if ("client1".equals(str)) {
            return Response.builder().status(1).build();
        } else if ("client2".equals(str)) {
            return Response.builder().status(2).build();
        }
        return Response.builder().status(999).build();
    }

    private void sendResponse(String s, OutputStream outputStream) throws IOException {
        outputStream.write(s.getBytes(StandardCharsets.UTF_8),0, s.length());
    }

    private Request deserializeRequest(InputStream inputStream) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        return ProtostuffUtil.deserialize(baos.toByteArray(), Request.class);

    }

    private Response processRequest(Request request) {
        assert request.getRMethod() != null;
        String methodName = request.getRMethod().getMethodName();
        Class<?> interfaceName = request.getRMethod().getInterfaceName();
        List<Class<?>> typeList = request.getRMethod().getTypeList();

        // 接口名、方法名、参数列表
        return null;
    }
}
