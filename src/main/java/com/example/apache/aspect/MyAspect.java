package com.example.apache.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author caogq
 * @description
 * @create 2021/10/22 14:09
 */
@Component
@Aspect
public class MyAspect {

    @Around("@annotation(com.example.apache.aspect.HotWind)")
    public Object arou(ProceedingJoinPoint point) throws Throwable{
        Object[] args = point.getArgs();
        System.out.println("========================="+ JSONObject.toJSONString(args));
        return point.proceed();
    }
}
