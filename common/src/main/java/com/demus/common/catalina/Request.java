package com.demus.common.catalina;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 10:46
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Request {
    private String url;
    private Header reqHeader;
    private Object data;
    private RMethod rMethod;
}
