package com.gxf.rpc.client;

import com.gxf.rpc.common.Calculator;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/28 下午1:31
 **/
public class CaculateClient {
    private static Calculator calculator;


    public static void main(String[] args) {
        calculator = ClientProxyFactory.getClientProxy(Calculator.class);
        int sum = calculator.add(1, 2);
        System.out.println("sum = " + sum);
        int sub = calculator.sub(5, 1);
        System.out.println("sub = " + sub);
    }
}
