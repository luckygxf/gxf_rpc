package com.gxf.rpc.server;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/28 下午2:21
 **/
public class RPCServerTest {

    public static void main(String[] args) {
        RPCServer.registerServer("add", CaculatorServer.class);
        RPCServer.registerServer("sub", CaculatorServer.class);
        RPCServer.start();
    }
}
