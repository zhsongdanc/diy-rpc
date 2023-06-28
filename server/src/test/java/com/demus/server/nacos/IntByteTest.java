package com.demus.server.nacos;

import com.demus.common.serialize.ObjectReader;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/28 20:55
 */
public class IntByteTest {
    public static void main(String[] args) {
        byte[] bytes = new byte[4];
        bytes[2] = 1;
        bytes[3] = 8;
        int i = ObjectReader.byteToInt(bytes);
        System.out.println(i);
    }
}
