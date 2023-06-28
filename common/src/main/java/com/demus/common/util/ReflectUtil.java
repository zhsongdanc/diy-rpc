package com.demus.common.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/27 21:07
 */
public class ReflectUtil {
    public static String getMainClassName(){
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        return stackTrace[stackTrace.length - 1].getClassName();
    }


    public static Set<Class<?>> getAllClass(String basePackage) {
        Set<Class<?>> res = new HashSet<>();
        String dirName = basePackage.replace(".", "/");
        Enumeration<URL> resources = null;
        try {
            resources = Thread.currentThread().getContextClassLoader().getResources(dirName);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                if ("file".equals(url.getProtocol())) {
                    findAndAddAllClasses(url.getPath(), basePackage, res);
                } else if ("jar".equals(url.getProtocol())) {
                    JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry jarEntry = entries.nextElement();
                        String name = jarEntry.getName();
                        if (name.charAt(0) == '/') {
                            name = name.substring(1);
                        }
                        if (name.startsWith(basePackage)) {
                            int idx = name.lastIndexOf("/");
                            if (idx != -1 && name.endsWith(".class")) {
                                String curPackageName = name.substring(0, idx).replace("/", ".");
                                String className = name.substring(idx + 1, name.length() - 6);
                                Class<?> curClass = Thread.currentThread().getContextClassLoader().loadClass(curPackageName + "." + className);
                                res.add(curClass);
                            }
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void findAndAddAllClasses(String filePath, String packageName, Set<Class<?>> res) {
        File curDir = new File(filePath);
        if (!curDir.exists() || !curDir.isDirectory()) {
            return;
        }

        File[] files = curDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.getName().endsWith(".class");
            }
        });
        for (File file : files) {
            if (file.isDirectory()) {
                findAndAddAllClasses(file.getAbsolutePath(), packageName + "." + file.getName(), res);
            } else {
                try {
                    Class<?> curClass = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + file.getName().substring(0, file.getName().length() -6));
                    res.add(curClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }



//    public static boolean isServiceClass(String )

    public static void main(String[] args) {
        System.out.println(ReflectUtil.class.getName());
    }

}
