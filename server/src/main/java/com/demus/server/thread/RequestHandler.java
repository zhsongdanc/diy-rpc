package com.demus.server.thread;

import com.demus.common.catalina.RpcRequest;
import com.demus.common.catalina.RpcResponse;
import com.demus.server.provider.ServiceProvider;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 13:32
 */
@Slf4j
public class RequestHandler {
    private ServiceProvider serviceProvider;

    public RequestHandler(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    // TODO ? 这里有可能一次接收同一个客户端的多个请求吗？
    public RpcResponse<Object> handle(RpcRequest request) {
        Object invokeResult = invokeTargetMethod(request);
        return RpcResponse.success(request.getReqId(), invokeResult);

    }

    public Object invokeTargetMethod(RpcRequest request) {
        String serviceName = request.getInterfaceName();
        Object serviceObj = serviceProvider.gerServiceProvider(serviceName);
        Method tarMethod = null;
        try {
            tarMethod = serviceObj.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
        } catch (NoSuchMethodException e) {
            log.error("no such method:{} in service:{}", request.getMethodName(), serviceName);
            e.printStackTrace();
        }
        Object res = null;
        try {
            res = tarMethod.invoke(serviceObj, request.getParameters());
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("invoke method:{} failed!", tarMethod.getName());
            e.printStackTrace();
        }
        return res;

    }


}
