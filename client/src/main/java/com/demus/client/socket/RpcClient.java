package com.demus.client.socket;

import com.demus.common.catalina.RpcRequest;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/29 11:36
 */
public abstract class RpcClient {
    public abstract void send(RpcRequest request);
}
