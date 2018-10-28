package com.gxf.rpc.util;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/10/28 下午2:50
 **/
public class ByteUtil {

    /**
     * 整型转换成byte 数组
     * */
    public static byte[] transInt2ByteArray(int n){
        byte[] intArray = new byte[4];
        intArray[0] = (byte) (0xff & n >> 24);
        intArray[1] = (byte) (0xff & n >> 16);
        intArray[2] = (byte) (0xff & n >> 8);
        intArray[3] = (byte) (0xff & n);
        return intArray;
    }


    /**
     * byte数组转换成int
     * */
    public static int byteBuf2Int(byte[] byteBuff){
        int res = 0;
        for(int i = 0; i < byteBuff.length; i++){
            res |= byteBuff[i];
            res <<= ((i + 1) * 8);
        }
        return res;
    }

}
