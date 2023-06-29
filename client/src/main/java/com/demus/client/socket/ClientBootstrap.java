package com.demus.client.socket;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/29 11:37
 */
public class ClientBootstrap {

    public static void main(String[] args) {
        RpcClient rpcClient = new SocketRpcClient();
        rpcClient.start("com.demus.common.inter.Phone");
    }
}
