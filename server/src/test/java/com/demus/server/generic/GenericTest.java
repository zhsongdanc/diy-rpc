package com.demus.server.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/27 23:17
 */
public class GenericTest {
    public static void main(String[] args) {
        Provider serviceProvider = new ProviderImpl();

        // 添加一个字符串服务提供者
        StringService stringService = new StringService();
        serviceProvider.addServiceProvider(stringService, "stringService");

        // 添加一个整数服务提供者
        IntService intService = new IntService();
        serviceProvider.addServiceProvider(intService, "intService");

        // 获取服务提供者
        StringService stringService2 = serviceProvider.getServiceProvider("stringService");
        IntService intService2 = serviceProvider.getServiceProvider("intService");

        // 使用服务提供者
        String result1 = stringService2.doSomething("hello");
        int result2 = intService2.doSomething(123);

        System.out.println(result1);
        System.out.println(result2);
    }
}

class ProviderImpl implements Provider {
    private Map<String, Object> serviceMap = new HashMap<>();

    @Override
    public <T> void addServiceProvider(T service, String serviceName) {
        serviceMap.put(serviceName, service);
    }

    public <T> T getServiceProvider(String serviceName) {
        return (T) serviceMap.get(serviceName);
    }
}

interface Provider{
    <T> void addServiceProvider(T service, String serviceName);
    <T> T getServiceProvider(String serviceName);
}

class StringService{
    String doSomething(String str) {
        return str + str;
    }
}
class IntService{
    int doSomething(int i){
        return i*i;
    }
}