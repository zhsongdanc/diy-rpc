package com.demus.server.nacos;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 11:31
 */
public class URLTest {

    public static void main(String[] args) throws MalformedURLException {
        String ip = "127.0.0.1";
        int port = 8888;
        URL url = new URL("http", ip, port, "");
        String path = url.getPath();
        System.out.println(path);
    }
}
