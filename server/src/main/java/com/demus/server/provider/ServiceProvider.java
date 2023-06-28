package com.demus.server.provider;

import com.demus.common.annota.MyServiceScan;
import com.demus.common.util.ReflectUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/27 13:49
 */
public interface ServiceProvider {

    <T> void addServiceProvider(String serviceName, T service);

    <T> T gerServiceProvider(String serviceName);

}
