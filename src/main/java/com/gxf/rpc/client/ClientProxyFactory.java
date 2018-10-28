package com.gxf.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/28 下午1:40
 **/
public class ClientProxyFactory {

    public static <T> T getClientProxy(Class<T> interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new ClientProxyHandler());
    }
}
