package com.example.apache.aspect;

import java.lang.annotation.*;

/**
 * @author caogq
 * @description
 * @create 2021/10/22 14:02
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HotWind {
}
