package com.example.apache;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSONObject;
import com.example.apache.controller.Student;
import com.example.apache.func.School;
import com.example.apache.pojo.AuthApplyResDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author caogq
 * @create 2021/8/5 18:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MyTest {



    @Test
    public void test001(){
//        Map<Object, Object> map = new HashMap<>();
        LocalDate parse = LocalDate.parse("2021-09-20", DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
        String tradeTimeStart = parse.atStartOfDay().format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
        String tradeTimeEnd = parse.plusDays(1).atStartOfDay().minus(1, ChronoUnit.SECONDS)
                .format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));

        System.out.println("==========");
    }

    @Test
    public void test02(){
        ttt("x","y","m","n");
    }

    public void ttt(String... str) {
        for (String s : str) {
            System.out.println("==============>:"+s);
        }

    }

    private School school = School.sc2;

    @Test
    public void test03() {
        Student xa = school.create("xa");
        System.out.println("========");
    }

    @Test
    public void test04() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        ClassLoader parent = classLoader.getParent();
        ClassLoader parent1 = parent.getParent();
        System.out.println("===========================");
    }


    @Test
    public void test05(){
        String s="{\"msg\":\"SUCCESS\",\"code\":\"0000\",\"listNo\":\"4501092021102110215104f1046001f64793\",\"authInstruction\":\"0082004108304251734083e357\"}";
        AuthApplyResDto authApplyResDto = JSONObject.parseObject(s, AuthApplyResDto.class);
        System.out.println("===============");
    }

    @Test
    public void test06() {
        System.out.println("=================0000000000000000000");
        FutureTask<Boolean> booleanFutureTask = new FutureTask<>(() -> {
            while (true) {
                Thread.sleep(8000);
                System.out.println("=============我在任务内");
                return true;
            }
        });
        System.out.println("=================11111111111111111111");
        Executors.newSingleThreadExecutor().execute(booleanFutureTask);
        Boolean aBoolean = null;
        try {
             aBoolean = booleanFutureTask.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("==============222222222222222222222>>>>"+aBoolean);

    }


    @Test
    public void test07() {
        Student student = new Student().setId("101").setName("李白").setSouce(BigDecimal.TEN).setAddress("河南");
        Optional<Student> optional = Optional.of(student);
        String aaa = optional.map(Student::getName).orElse("aaa");
        String bbb = optional.map(Student::getName).orElseGet(() -> "bbb");

        Map<String, String> describe = null;
        try {
            describe = BeanUtils.describe(student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("======================"+describe);
    }




}
