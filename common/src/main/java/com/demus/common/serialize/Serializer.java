package com.demus.common.serialize;

import com.demus.common.serialize.protostuff.ProtostuffUtil;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 12:10
 */
public interface Serializer {

    int Protostuff = 0;
    int Jackson = 1;

    public <T> byte[] serialize(T obj);

    public Object deserialize(byte[] bytes, Class<?> clazz);


    public static Serializer getByCode(int code) {
        switch (code){
            case 0:
                return new ProtostuffUtil();
            default:
                return new ProtostuffUtil();
        }
    }
}
