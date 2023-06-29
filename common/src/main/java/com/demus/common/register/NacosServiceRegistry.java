package com.demus.common.register;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import java.net.InetSocketAddress;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 11:20
 */
// TODO 服务关闭时注销服务
@Slf4j
public class NacosServiceRegistry implements ServiceRegistry{

    private NamingService namingService;

    // TODO 这里感觉写的不规范
    public NacosServiceRegistry() {
        try {
            this.namingService = NamingFactory.createNamingService("http://localhost:8848");
        } catch (NacosException e) {
            log.error("failed to create registry");
            e.printStackTrace();
        }
    }

    @Override
    public void registerService(String serviceName, String ip, int port) {
        try {
            namingService.registerInstance(serviceName, ip, port);

        } catch (NacosException e) {
            log.error("failed to register service:{}", serviceName);
            e.printStackTrace();
        }
    }

    @Override
    public InetSocketAddress getService(String serviceName) {
        List<Instance> instances = null;
        try {
            instances = namingService.getAllInstances(serviceName);
        } catch (NacosException e) {
            log.error("failed to find service ,serviceName:{}", serviceName);
            e.printStackTrace();
        }
        if (instances == null || instances.size() == 0) {
            log.error("no such service, serviceName is :{}", serviceName);
            throw new RuntimeException("no such service");
        }
        for (Instance instance : instances) {
            String ip = instance.getIp();
            int port = instance.getPort();
            log.info("the address of service:{} is {}:{}", serviceName, ip, port);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);

            return inetSocketAddress;
        }
        return null;
    }
}
