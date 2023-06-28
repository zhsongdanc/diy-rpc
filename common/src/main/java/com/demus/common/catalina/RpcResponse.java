package com.demus.common.catalina;

import lombok.Data;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 14:15
 */
@Data
public class RpcResponse<T> {

    private static int successStatus = 200;
    public static int failStatus = 500;
    private String reqId;
    private int status;

    private T data;

    public static <T> RpcResponse<T> success(String reqId, T data) {
        RpcResponse<T> rpcResponse = new RpcResponse<>();
        rpcResponse.setReqId(reqId);
        rpcResponse.setData(data);
        rpcResponse.setStatus(successStatus);

        return rpcResponse;
    }

    public static <T> RpcResponse<T> fail(String reqId, int status) {
        RpcResponse<T> rpcResponse = new RpcResponse<>();
        rpcResponse.setReqId(reqId);
        rpcResponse.setStatus(status);

        return rpcResponse;
    }
}
