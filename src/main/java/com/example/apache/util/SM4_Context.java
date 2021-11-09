package com.example.apache.util;

/**
 * @author wangKai
 * @version 1.0
 * @date 2020/11/21
 * @description SM4加密工具
 */
public class SM4_Context {
    public int mode;

    public long[] sk;

    public boolean isPadding;

    public SM4_Context()
    {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new long[32];
    }
}
