package com.demus.server.nacos;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/27 22:27
 */
public class JarTest {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\maven\\download\\apache-maven-3.6.3\\maven-repo\\ch\\qos\\logback\\logback-classic\\1.2.3\\logback-classic-1.2.3.jar");
        JarFile jar = new JarFile(file);

        // 从此jar包 得到一个枚举类
        Enumeration<JarEntry> entries = jar.entries();
        // 同样的进行循环迭代
        while (entries.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }
            // 如果前半部分和定义的包名相同
//                if (name.startsWith(packageDirName)) {
//                    int idx = name.lastIndexOf('/');
//                    // 如果以"/"结尾 是一个包
//                    if (idx != -1) {
//                        // 获取包名 把"/"替换成"."
//                        packageName = name.substring(0, idx)
//                                .replace('/', '.');
//                    }
//                    // 如果可以迭代下去 并且是一个包
//                    if ((idx != -1) || recursive) {
//                        // 如果是一个.class文件 而且不是目录
//                        if (name.endsWith(".class")
//                                && !entry.isDirectory()) {
//                            // 去掉后面的".class" 获取真正的类名
//                            String className = name.substring(
//                                    packageName.length() + 1, name
//                                            .length() - 6);
//                            try {
//                                // 添加到classes
//                                classes.add(Class
//                                        .forName(packageName + '.'
//                                                + className));
//                            } catch (ClassNotFoundException e) {
//                                // log
//                                // .error("添加用户自定义视图类错误 找不到此类的.class文件");
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
        }

    }
}
