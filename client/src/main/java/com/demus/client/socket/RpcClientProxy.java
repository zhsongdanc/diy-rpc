package com.demus.client.socket;

import com.demus.common.catalina.RpcRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/29 13:39
 */
public class RpcClientProxy implements InvocationHandler {

    private RpcClient rpcClient;

    public RpcClientProxy(RpcClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//        RpcRequest request = RpcRequest.builder()
//                .reqId();


        return null;
    }
}
