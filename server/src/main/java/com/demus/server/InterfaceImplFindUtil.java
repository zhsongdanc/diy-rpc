package com.demus.server;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/26 20:17
 */
@Slf4j
public class InterfaceImplFindUtil {

    public static List<Class<?>> findClasses(String interfaceName) throws Exception{
        List<Class<?>> classes = new ArrayList<>();
        Class<?> interfaceClass = null;
        try {
            interfaceClass = Class.forName(interfaceName);
        } catch (ClassNotFoundException e) {
            log.error("不存在该接口，{}", interfaceName);
            e.printStackTrace();
        }

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = contextClassLoader.getResources("");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if (url.getProtocol().equals("file")) {
                recurFind(interfaceClass, classes, new File(url.getPath()));
            }
        }

        return classes;
    }

    public static void recurFind(Class<?> interfaceClass, List<Class<?>> classes, File directory) throws Exception{
        if (!directory.exists()) {
            return;
        }


        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                recurFind(interfaceClass, classes, file);
            } else {
                if (file.getName().endsWith(".class")) {
                    String className = file.getName().substring(0, file.getName().length() - 6);
                    Class<?> curClass = Class.forName(directory.getName() + "." + className);
                    if (interfaceClass.isAssignableFrom(curClass) && !Modifier.isAbstract(curClass.getModifiers())) {
                        classes.add(curClass);
                    }
                }
            }

        }
    }


    public static void main(String[] args) throws Exception{
        // test
        String interfaceName = "com.demus.common.inter.Phone";
        List<Class<?>> classes = findClasses(interfaceName);
        assert classes.size() == 1;
    }
}
