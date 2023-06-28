package com.demus.server.provider;

import com.demus.server.provider.ServiceProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/27 23:46
 */
@Slf4j
public class ServiceProviderImpl implements ServiceProvider {
    // TODO 这里如果存入Class类型的对象需要每次生成一个对象
    // TODO 这里用线程安全的map，主线程写，处理线程读
    private static Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    @Override
    public <T> void addServiceProvider(String serviceName, T service) {
        if (serviceMap.containsKey(serviceName)) {
            return;
        }

        serviceMap.put(serviceName, service);
        log.info("added service:{} to provider", serviceName);
    }

    @Override
    public <T> T gerServiceProvider(String serviceName) {
        Object serviceObj = serviceMap.get(serviceName);
        if (serviceObj == null) {
            log.error("no such service, serviceNam is: {}", serviceName);
            throw new RuntimeException("不存在该类型服务");
        }
        return (T)serviceObj;
    }
}
