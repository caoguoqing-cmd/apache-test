package com.example.apache.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author caogq
 * @create 2021/8/12 9:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

    private Integer source;
}
