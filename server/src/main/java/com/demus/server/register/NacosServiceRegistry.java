package com.demus.server.register;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 11:20
 */
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
            log.error("failed to register service:{}", serviceName);
        } catch (NacosException e) {
            log.error("register service failed!");
            e.printStackTrace();
        }
    }

    @Override
    public String getService(String serviceName) {
        List<Instance> instances = null;
        try {
            instances = namingService.getAllInstances(serviceName);
        } catch (NacosException e) {
            log.error("failed to find service ,serviceName:{}", serviceName);
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(instances)) {
            log.error("no such service, serviceName is :{}", serviceName);
            throw new RuntimeException("no such service");
        }
        for (Instance instance : instances) {
            String ip = instance.getIp();
            int port = instance.getPort();
            System.out.println("id = " + ip);
            System.out.println("port = " + port);
            return ip + port;
        }
        return null;
    }
}
