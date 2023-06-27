package com.demus.common.annota;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Author: demussong
 * @Description:
 * TODO 如果basePackage没有值，那么扫描当前类所在的包，这个逻辑应该是代码里写的吗？
 * @Date: 2023/6/27 13:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyServiceScan {
    String basePackage() default "";
}
