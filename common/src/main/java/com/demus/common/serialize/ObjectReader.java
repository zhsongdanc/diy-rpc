package com.demus.common.serialize;

import com.demus.common.catalina.RpcRequest;
import com.demus.common.catalina.RpcResponse;
import com.demus.common.enums.PackageTypeEnum;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/28 12:43
 */
public class ObjectReader {
    public static int magicNumber = 0x23474a12;

    public static Object read(InputStream inputStream) throws IOException {
        byte[] tmp = new byte[4];
        inputStream.read(tmp);
        int magic = byteToInt(tmp);
        if (magic != magicNumber) {
            throw new RuntimeException("no support");
        }

        Class<?> packageTypeClass = null;
        inputStream.read(tmp);
        int type = byteToInt(tmp);
        if (type == PackageTypeEnum.REQ_PACKAGE.getCode()) {
            packageTypeClass = RpcRequest.class;
        } else if (type == PackageTypeEnum.RESP_PACKAGE.getCode()) {
            packageTypeClass = RpcResponse.class;
        }

        inputStream.read(tmp);
        int serializeType = byteToInt(tmp);

        inputStream.read(tmp);
        int dataLen = byteToInt(tmp);

        byte[] data = new byte[dataLen];
        inputStream.read(data);

        Serializer serializer = Serializer.getByCode(serializeType);
        return serializer.deserialize(data, packageTypeClass);

    }

    public static void write(OutputStream outputStream, Object obj) throws IOException{
        outputStream.write(intToByte(magicNumber));

        if (obj instanceof RpcRequest) {
            outputStream.write(intToByte(PackageTypeEnum.REQ_PACKAGE.getCode()));
        } else if (obj instanceof RpcResponse) {
            outputStream.write(intToByte(PackageTypeEnum.RESP_PACKAGE.getCode()));
        } else {
            throw new RuntimeException("package type not support!");
        }

        outputStream.write(intToByte(Serializer.Protostuff));

        byte[] serialize = Serializer.getByCode(Serializer.Protostuff).serialize(obj);

        outputStream.write(intToByte(serialize.length));

    }

    public static int byteToInt(byte[] b) {
        return ((b[0] & 0xFF) << 24) +
                ((b[1] & 0xFF) << 16) +
                ((b[2] & 0xFF) << 8) +
                ((b[3] & 0xFF));
    }

    public static byte[] intToByte(int x) {
        byte[] res = new byte[4];
        res[3] = (byte)(x & 0xff);
        res[2] = (byte)((x >>>8) & 0xff);
        res[1] = (byte)((x >>>16) & 0xff);
        res[0] = (byte)((x >>>24) & 0xff);
        return res;
    }

}
