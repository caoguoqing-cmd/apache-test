package com.example.apache.pojo;

/**
 * @author caogq
 * @description
 * @create 2021/10/19 11:40
 */
public class TT {

    public static void main(String[] args) {
        System.out.println("================:1、"+TT.class.getClassLoader());
        QQ qq = new QQ();
        qq.say();
    }
}
