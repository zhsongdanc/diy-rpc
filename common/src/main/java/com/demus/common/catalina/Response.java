package com.demus.common.catalina;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 10:46
 */
@AllArgsConstructor
@Data
@Builder
public class Response {
    private int status;
    private Header respHeader;
    private Object data;
}
