package com.example.apache;

import com.example.apache.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author caogq
 * @create 2021/8/11 16:31
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AlgorithmTest {

    @Test
    public void test01() {
        int[] arr = {1,3,5,5,8,8,10};
        int i = quChong(arr);
        System.out.println("==================个数："+i);
    }

    public int quChong(int[] arr){
        if (arr.length == 0) {
            return 0;
        }
        // 第一个元素
        int i = 0;
        // 第二个元素
        for (int j = 1; j < arr.length; j++) {

            if (arr[i] != arr[j]) {
                i++;
                arr[i] = arr[j];
            }
        }
        return i+1;
    }

    @Test
    public void test02(){
        List<Person> list = new ArrayList<Person>(){{
            add(new Person().setId(1).setName("李白").setAge(20).setSource(95));
            add(new Person().setId(2).setName("杜甫").setAge(18).setSource(93));
            add(new Person().setId(3).setName("苏东坡").setAge(19).setSource(94));
            add(new Person().setId(4).setName("白居易").setAge(28).setSource(98));
            add(new Person().setId(6).setName("杜牧").setAge(18).setSource(96));
            add(new Person().setId(5).setName("李商隐").setAge(36).setSource(96));
            add(new Person().setId(7).setName("孟浩然").setAge(28).setSource(93));
        }};

        List<Person> list1 = list.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
        List<Person> list2 = list.stream().sorted(Comparator.comparing(Person::getAge).reversed()).collect(Collectors.toList());
        List<Person> list3 = list.stream().sorted(Comparator.comparing(Person::getAge,Comparator.reverseOrder())).collect(Collectors.toList());

        List<Person> list4 = list.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getSource)).collect(Collectors.toList());
        System.out.println("======================");
    }

    @Test
    public void test03(){
        String x = "2.13";
        String substring = x.substring(0, 1);
        String substring1 = x.substring(1);

        int count = 10%8;
        int count2 = 10/8;


        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
        System.out.println("===============");
    }

    @Test
    public void test04(){
        List<String> list = new ArrayList<String>(){{
            add("x");
            add("y");
            add("z");
            add("b");
            add("c");
            add("a");
        }};

        for (int i = 1; i <= 3; i++) {
            List<String> sub = list.subList(2 * (i-1), 2*i );
            System.out.println("=========== i :"+i +"      :"+sub.toString());
        }
    }

    @Test
    public void test05() {

        LocalDateTime start = LocalDate.now().plusDays(-1).atStartOfDay();
        LocalDateTime end = LocalDate.now().atStartOfDay().minus(1, ChronoUnit.SECONDS);

        LocalDateTime yesterday = LocalDateTime.now().plusDays(-1L);
//        LocalDateTime start = LocalDateTime.of(yesterday.toLocalDate(), LocalTime.MIN);
//        LocalDateTime end = LocalDateTime.of(yesterday.toLocalDate(), LocalTime.MAX);
        LocalDateTime clearTime = LocalDateTime.now();
        String clearStr = (null == clearTime) ? "-" : clearTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("==============");
    }

    @Test
    public void test06() {


        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());

        String a="123";
        String b= "xx";

        String concat = a.concat(b);


        List<String> list = new ArrayList<String>(){{
            add("x");
            add("a");
            add("s");
            add("d");
            add("f");
        }};
        List<String> d = list.stream().filter(s -> s.equals("d")).collect(Collectors.toList());
        list.removeAll(d);

        d.addAll(list);
        System.out.println("=============="+d);
    }


    @Test
    public  void createSign(){
        LocalDateTime minus = LocalDate.now().plusDays(1).atStartOfDay().minus(1, ChronoUnit.SECONDS);


        SortedMap<String, Object> parameterMap = new TreeMap<>();
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("a","李白");
            put("b","杜甫");
        }};
        parameterMap.putAll(map);
        String secret = "wocao";
        StringBuffer buffer = new StringBuffer(secret);
        parameterMap.forEach((k,v)->{
            buffer.append(k).append(v);
        });
        String str = buffer.append(secret).toString();

        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        String s = DigestUtils.md5Hex(str);

        System.out.println("=================：" +s);
    }





}
