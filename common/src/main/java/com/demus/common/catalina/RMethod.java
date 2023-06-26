package com.demus.common.catalina;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RMethod {
    private Class<?> interfaceName;
    private String methodName;
    private List<Class<?>> typeList;
}
