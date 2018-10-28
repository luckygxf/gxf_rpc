package com.gxf.rpc.common;

/**
 * @Author: <guanxianseng@163.com>
 * @Description: rpc客户端
 * @Date: Created in : 2018/10/28 下午1:20
 **/
public interface Calculator {

    /**
     * 计算 n1 + n2
     */
    int add(int n1, int n2);

    /**
     * 计算n1 - n2
     * */
    int sub(int n1, int n2);
}
