package com.example.apache.controller;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author caogq
 * @create 2021/8/9 10:46
 */
@Data
@Accessors(chain = true)
@Component
public class StudentX {

    private String name;//姓名

    private BigDecimal souce;

}
