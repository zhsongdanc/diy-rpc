package com.demus.common.catalina;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 12:45
 */
@Data
@Builder
public class RpcRequest {
    private String reqId;
    private String interfaceName;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
}
