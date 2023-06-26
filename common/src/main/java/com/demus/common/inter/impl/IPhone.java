package com.demus.common.inter.impl;

import com.demus.common.inter.Phone;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 17:25
 */
public class IPhone implements Phone {

    @Override
    public void call(String name) {
        System.out.println("call" + name  + "by iphone!");
    }
}
