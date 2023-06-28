package com.demus.server.nacos;

import java.net.ServerSocket;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/28 22:02
 */
public class InetAddrTest {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(2945);
        System.out.println(serverSocket.getInetAddress());
    }
}
