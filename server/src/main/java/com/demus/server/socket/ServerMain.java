package com.demus.server.socket;

import com.demus.common.annota.MyServiceScan;
import com.demus.server.thread.ProcessRequestTask;
import com.demus.server.thread.ThreadPoolUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

import lombok.extern.slf4j.Slf4j;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/6/21 11:20
 */
@Slf4j
@MyServiceScan
public class ServerMain {

    public static void main(String[] args) throws  Exception{
        RpcServer rpcServer = new SocketServer(9629);
        rpcServer.start();
        int serverPort = 9629;
        ServerSocket ss = new ServerSocket(serverPort);
        log.info("waiting for connection on serverPort {}", serverPort);
        Executor threadPool = ThreadPoolUtil.getProcessRequestThreadPool();
        while (true) {
            Socket socket = ss.accept();
            log.info("client:{} connected", socket.getInetAddress().getHostName());
            ProcessRequestTask processRequestTask = new ProcessRequestTask(socket);
//            new Thread(processRequestTask).start();
            threadPool.execute(processRequestTask);
        }
    }


    public static Object deserialize(InputStream is) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] data = baos.toByteArray();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        return ois.readObject();
    }

    public static Object deserialize2(InputStream is) throws IOException, ClassNotFoundException {
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bufferedos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] data = baos.toByteArray();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        return ois.readObject();
    }

}


