package com.example.apache.pojo;

/**
 * @author caogq
 * @description
 * @create 2021/10/19 11:42
 */
public class QQ {

    public void say(){
        System.out.println("============2:"+this.getClass().getClassLoader());
    }
}
