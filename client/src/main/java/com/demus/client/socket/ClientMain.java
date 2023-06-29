package com.demus.client.socket;

import com.demus.common.catalina.RMethod;
import com.demus.common.catalina.Request;
import com.demus.common.inter.Phone;
import com.demus.common.serialize.protostuff.ProtostuffUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;


/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 10:27
 */
@Slf4j
public class ClientMain {

    public static void main(String[] args) {
        Socket socket = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9629);

        try {
            socket.connect(inetSocketAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Request request = createRequest();
        byte[] reqBytes = serializeReq(request);
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
            outputStream.write(reqBytes, 0, reqBytes.length);
        } catch (IOException e) {
            log.error("发送请求异常");
            e.printStackTrace();
        }finally {
            try {
                assert outputStream != null;
                outputStream.close();
            } catch (IOException e) {
                log.error("关闭outputstream失败");
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            log.error("close socket error");
            e.printStackTrace();
        }

//        String req = "hello";
//        outputStream.write("hello".getBytes(StandardCharsets.UTF_8), );
//        BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
//        while (true) {
//            OutputStream outputStream = socket.getOutputStream();
//            BufferedWriter bufferedWriter = new BufferedWriter(outputStream);
//        }
    }

    private static Request createRequest() {
        Request request = new Request();
        RMethod rMethod = RMethod.builder()
                .methodName("call")
                .interfaceName(Phone.class)
                .typeList(Collections.singletonList(String.class))
                .build();
        request.setRMethod(rMethod);
        return request;
    }

    private static byte[] serializeReq(Request request) {
        return new ProtostuffUtil().serialize(request);
    }

}
