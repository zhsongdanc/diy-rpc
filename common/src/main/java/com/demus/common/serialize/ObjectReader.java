package com.demus.common.serialize;

import com.demus.common.catalina.RpcRequest;
import com.demus.common.catalina.RpcResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 12:43
 */
public class ObjectReader {
    public static int magicNumber = 0x23474a12;

    public static RpcRequest read(InputStream inputStream) throws IOException {
        byte[] tmp = new byte[4];
        inputStream.read(tmp);
        int magic = byteToInt(tmp);
        if (magic != magicNumber) {
            throw new RuntimeException("no support");
        }
        inputStream.read(tmp);
        int type = byteToInt(tmp);

        inputStream.read(tmp);
        int serializeType = byteToInt(tmp);

        inputStream.read(tmp);
        int dataLen = byteToInt(tmp);

        byte[] data = new byte[dataLen];
        inputStream.read(data);

        Serializer serializer = Serializer.getByCode(serializeType);
        return serializer.deserialize(data, RpcRequest.class);

    }

    public static byte[] write(OutputStream outputStream, RpcResponse<Object> rpcResponse) throws IOException{
//        outputStream.write(rpcRequest.getMagicNum());
//
//        outputStream.write(rpcRequest.getType());
//
//        outputStream.write(rpcRequest.getSerializeType());
//        outputStream.write(rpcRequest.getLen());
//        outputStream.write(rpcRequest.getData());
        return null;
    }

    public static int byteToInt(byte[] b) {
        return ((b[0] & 0xFF) << 24) +
                ((b[1] & 0xFF) << 16) +
                ((b[2] & 0xFF) << 8) +
                ((b[3] & 0xFF));
    }


}
