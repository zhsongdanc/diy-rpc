package com.demus.server;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/26 20:55
 */
import com.demus.common.inter.impl.IPhone;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class InterfaceImplFinder {

    public static List<Class<?>> find(String interfaceName) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        Class<?> interfaceClass = Class.forName(interfaceName);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources("");
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource.getProtocol().equals("file")) {
                File file = new File(resource.getPath());
                findClasses(file, interfaceClass, classes);
            }
        }
        return classes;
    }

    private static void findClasses(File directory, Class<?> interfaceClass, List<Class<?>> classes) throws ClassNotFoundException {
        if (!directory.exists()) {
            return;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findClasses(file, interfaceClass, classes);
            } else if (file.getName().endsWith(".class")) {
                String className = file.getName().substring(0, file.getName().length() - 6);
                Class<?> clazz = Class.forName(directory.getName() + "." + className);
                if (interfaceClass.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
                    classes.add(clazz);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        // test
        new IPhone();
        String interfaceName = "com.demus.common.inter.Phone";
        List<Class<?>> classes = find(interfaceName);
        assert classes.size() == 1;
    }
}

