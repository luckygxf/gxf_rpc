package com.gxf.rpc.server;

import com.gxf.rpc.util.ByteUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/28 下午1:59
 **/
public class RPCServer {
    //服务名 --> server
    private static Map<String, Class> serverContainer = new HashMap<String, Class>();
    private static int listenport = 8080;
    private static ServerSocket serverSocket;


    /**
     * 注册服务
     * */
    public static void registerServer(String methodName, Class server){
        serverContainer.put(methodName, server);
    }

    /**
     * 启动rpc, 监听端口
     * */
    public static void start(){
        try {
            serverSocket = new ServerSocket(listenport);
            System.out.println("GXF RPC start");
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Receive client connetion");
                InputStream inputStream = clientSocket.getInputStream();
                //读method_name_length
                byte[] methodNameLengthByteBuff = new byte[4];
                int length = 4;
                int readCount = 0;
                int offset = 0;
                while(readCount < length){
                    readCount = inputStream.read(methodNameLengthByteBuff, offset, length - offset);
                    offset += readCount;
                }
                readCount = 0;
                offset = 0;

                //读method_name
                int methodNameLength = ByteUtil.byteBuf2Int(methodNameLengthByteBuff);
                System.out.println("methodNameLength: " + methodNameLength);
                byte[] methodNameByteBuff = new byte[methodNameLength];
                while(readCount < methodNameLength){
                    readCount = inputStream.read(methodNameByteBuff, offset, methodNameLength - offset);
                    offset += readCount;
                }
                String methodName = new String(methodNameByteBuff);
                System.out.println("methodName: " + methodName);
                readCount = 0;
                offset = 0;

                //读n1
                byte[] n1ByteBuff = new byte[4];
                length = 4;
                while(readCount < length){
                    readCount = inputStream.read(n1ByteBuff, offset, length - offset);
                    offset += readCount;
                }
                int n1 = ByteUtil.byteBuf2Int(n1ByteBuff);
                System.out.println("n1 = " + n1);
                readCount = 0;
                offset = 0;

                //读n2
                byte[] n2ByteBuff = new byte[4];
                length = 4;
                while(readCount < length){
                    readCount = inputStream.read(n2ByteBuff, offset, length - offset);
                    offset += readCount;
                }
                int n2 = ByteUtil.byteBuf2Int(n2ByteBuff);
                System.out.println("n2 = " + n2);

                //计算结果
                int resInt = getResult(methodName, n1, n2);
                System.out.println("resInt : " + resInt);

                //结果返回客户端
                OutputStream clientOutputStream = clientSocket.getOutputStream();
                byte[] resBuff = ByteUtil.transInt2ByteArray(resInt);
                clientOutputStream.write(resBuff, 0, 4);
                clientOutputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 调用服务端服务
     * */
    private static int getResult(String methodName, int n1, int n2){
        Class clazz = serverContainer.get(methodName);
        int res = -1;
        try {
            Object serverObject = clazz.newInstance();
            Method method = clazz.getMethod(methodName, int.class, int.class);
            Object resObj = method.invoke(serverObject, n1, n2);
            System.out.println("resObj = " + resObj);
            res = (Integer) resObj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
