package com.demus.server;

import com.demus.common.annota.MyService;
import com.demus.common.annota.MyServiceScan;
import com.demus.common.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:47
 */
@Slf4j
public abstract class RpcServer {
    protected ServiceProvider serviceProvider;
    protected ServiceRegistry serviceRegistry;


    protected abstract void start() throws Exception;

    public void publishService(Object service, String serviceName) {

    }

    // 扫描服务放进去
    public void scanService() {
        String mainClassName = ReflectUtil.getMainClassName();

        Class<?> curClass = null;
        try {
            curClass = Class.forName(mainClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MyServiceScan annotation = curClass.getAnnotation(MyServiceScan.class);
        if (annotation == null) {
            throw new RuntimeException("主类没有标注@MyServiceScan");
        }
        String basePackage = annotation.basePackage();
        int lastPointIndex = mainClassName.lastIndexOf(".");
        String mainPackageName = mainClassName.substring(0, lastPointIndex);
        if (basePackage == null || ("").equals(basePackage)) {
            basePackage = mainPackageName;
        }
        Set<Class<?>> allClass = ReflectUtil.getAllClass(mainClassName);
        for (Class<?> clazz : allClass) {
            Object service = null;
            try {
                service = clazz.newInstance();
            } catch (InstantiationException e) {
                log.error("反射生成对象错误");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (clazz.isAnnotationPresent(MyService.class)) {
                String serviceName = clazz.getAnnotation(MyService.class).serviceName();
                if ("".equals(serviceName)) {
                    for (Class<?> clazzInterface : clazz.getInterfaces()) {
                        publishService(service, clazzInterface.getName());
                    }
                } else {
                    publishService(service, serviceName);
                }
            }
        }
    }
}
