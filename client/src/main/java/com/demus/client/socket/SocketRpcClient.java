package com.demus.client.socket;


import com.demus.common.catalina.RpcRequest;
import com.demus.common.catalina.RpcResponse;
import com.demus.common.register.NacosServiceRegistry;
import com.demus.common.register.ServiceRegistry;
import com.demus.common.serialize.ObjectReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/29 11:37
 */
@Slf4j
public class SocketRpcClient extends RpcClient{

    public void start(String serviceName) {
        ServiceRegistry serviceRegistry = new NacosServiceRegistry();
        InetSocketAddress clientAddr = serviceRegistry.getService(serviceName);
        try(Socket socket = new Socket(clientAddr.getHostName(), clientAddr.getPort())) {
            RpcRequest request = RpcRequest.builder()
                    .reqId("42009834")
                    .methodName("call")
                    .interfaceName("com.demus.common.pojo.Phone")
                    .parameters(new Object[]{"demus"})
                    .parameterTypes(new Class[]{String.class})
                    .build();
            ObjectReader.write(socket.getOutputStream(), request);
            RpcResponse rpcResponse = (RpcResponse)ObjectReader.read(socket.getInputStream());
            log.info("get data from reqId:{}", rpcResponse.getReqId());
            log.info("get data from status:{}", rpcResponse.getStatus());
            log.info("get data from server:{}", rpcResponse.getData());

        }catch (IOException e) {
            log.error("client err!");
            e.printStackTrace();
        }

    }



}
