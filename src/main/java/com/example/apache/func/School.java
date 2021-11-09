package com.example.apache.func;

import com.example.apache.controller.Student;

/**
 * @author caogq
 * @description
 * @create 2021/10/15 14:23
 */
@FunctionalInterface
public interface School {
    School sc1 = (s)->{
        Student student = new Student();
        System.out.println("=用的1");
        return student.setName(s);
    };

    School sc2 = (s)->{
        Student student = new Student();
        System.out.println("=用的2");
        return student.setName(s);
    };

    Student create(String s);
}
