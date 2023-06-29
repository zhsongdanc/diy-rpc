package com.demus.server.socket;

import com.demus.common.annota.MyService;
import com.demus.common.pojo.Phone;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 17:25
 */
@MyService
public class IPhone implements Phone {

    @Override
    public String call(String name) {
        System.out.println("call " + name  + " by iphone!");
        return "call " + name  + " by iphone!";
    }
}
