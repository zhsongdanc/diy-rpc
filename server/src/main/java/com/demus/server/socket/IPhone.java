package com.demus.server.socket;

import com.demus.common.annota.MyService;
import com.demus.common.inter.Phone;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 17:25
 */
@MyService
public class IPhone implements Phone {

    @Override
    public void call(String name) {
        System.out.println("call" + name  + "by iphone!");
    }
}
