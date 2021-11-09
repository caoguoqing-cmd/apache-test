package com.example.apache.controller;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author caogq
 * @create 2021/8/9 10:46
 */
@Data
@Accessors(chain = true)
public class Student {

    private String id;//编号
    private String name;//姓名

    private BigDecimal souce;
    private String address;
}
