package com.gxf.rpc.server;

import com.gxf.rpc.common.Calculator;

/**
 * @Author: <guanxiangfei@meituan.com>
 * @Description:
 * @Date: Created in : 2018/10/28 下午1:22
 **/
public class CaculatorServer implements Calculator {

    /**
     * 计算 n1 + n2
     * */
    public int add(int n1, int n2) {
        return n1 + n2;
    }
}
