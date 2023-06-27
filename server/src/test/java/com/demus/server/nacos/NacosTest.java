package com.demus.server.nacos;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 18:43
 */

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import java.util.List;
import org.junit.jupiter.api.Test;

public class NacosTest {

    @Test
    public void say() {
        System.out.println("hi");
    }

    @Test
    public void registerService() throws NacosException {
        NamingService naming = NamingFactory.createNamingService("http://localhost:8848");
        naming.registerInstance("helloService", "11.11.11.11", 8888);

    }

    @Test
    public void getService() throws NacosException {
        NamingService naming = NamingFactory.createNamingService("http://localhost:8848");
        List<Instance> instances = naming.getAllInstances("helloService");
        for (Instance instance : instances) {
            String ip = instance.getIp();
            int port = instance.getPort();
            System.out.println("id = " + ip);
            System.out.println("port = " + port);
        }
    }

}
