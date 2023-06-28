package com.demus.server.socket;

import com.demus.common.annota.MyServiceScan;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/28 21:36
 */
@MyServiceScan
public class ServerBootStrap {
    public static void main(String[] args) throws Exception{
        RpcServer rpcServer = new SocketServer(8973);
        rpcServer.start();
    }
}
