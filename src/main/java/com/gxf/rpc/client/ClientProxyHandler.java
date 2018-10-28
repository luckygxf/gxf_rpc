package com.gxf.rpc.client;

import com.gxf.rpc.util.ByteUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author: <guanxianseng@163.com>
 * @Description: 客户端代理
 * @Date: Created in : 2018/10/28 下午1:30
 **/
public class ClientProxyHandler implements InvocationHandler {
    private String host = "127.0.0.1";
    private int port = 8080;
    private Socket clientSocket = null;
    private InputStream inputStream;


    /*********************
    * 作用: 单端口, 单服务, 两个int 参数,
     *      ---------------------------------------------
    * 协议: | int              |string       | int | int |
    * 字段: | method_name_size | method_name |  n1 |  n2 |
     * *    ---------------------------------------------
    * *****/
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        sendInvokeMessage2Server(method, args);
        int res = receiveResultFromServer();
        System.out.println("res: " + res);
        return res;
    }


    /**
     * 发送调用信息给服务端
     * */
    private void sendInvokeMessage2Server(Method method, Object[] args){
        System.out.println("start client proxy ");
        try {
            clientSocket = new Socket(host, port);
            //method_name
            String methodName = method.getName();
            System.out.println("methodName: " + methodName);
            int n1 = (Integer) args[0];
            int n2 = (Integer) args[1];
            System.out.println("n1 = " + n1 + ", n2 = " + n2);

            //write msg 2 server
            OutputStream clientOutputStream = clientSocket.getOutputStream();
            clientOutputStream.write(ByteUtil.transInt2ByteArray(methodName.length()), 0, 4);
            clientOutputStream.write(methodName.getBytes());
            clientOutputStream.write(ByteUtil.transInt2ByteArray(n1), 0, 4);
            clientOutputStream.write(ByteUtil.transInt2ByteArray(n2), 0, 4);
            clientOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从服务端接收消息
     * */
    private int receiveResultFromServer(){
        int res = -1;
        try {
            InputStream inputStream = clientSocket.getInputStream();
            byte[] byteBuff = new byte[4];
            int length = 4;
            int readCount = 0;
            int offset = 0;
            while(readCount < length){
                readCount = inputStream.read(byteBuff, offset, length - offset);
                offset += readCount;
            }
            res = ByteUtil.byteBuf2Int(byteBuff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }



}
