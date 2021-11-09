package com.example.apache;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Tuple;
import com.example.apache.controller.StudentX;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author caogq
 * @create 2021/8/5 18:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class StringTest {

    @Test
    public void test01(){
        boolean numeric = StringUtils.isNumeric("123");
        boolean numeric2 = StringUtils.isNumeric("ag");
        Tuple tuple = new Tuple("x","y");


        System.out.println("==========================");
    }

    @Test
    public void test02(){
        String fileName = String.format("%s_%s_%s","wo","cao","nimei");
        System.out.println("========="+fileName);
    }

    @Test
    public void test03(){
        LocalDate now = LocalDate.now();
        System.out.println("========="+now);
        LocalDateTime localDateTime = now.atStartOfDay();
        System.out.println("============"+localDateTime);
        LocalDateTime localDateTime1 = now.atStartOfDay();
        LocalDateTime localDateTime2 = now.plusDays(1).atStartOfDay().minusNanos(1);
        LocalDateTime localDateTime3 = now.plusDays(1).atStartOfDay().minus(1, ChronoUnit.MILLIS);
        System.out.println("============"+localDateTime2);

        LocalDateTime localDateTime4 = LocalDate.now().atStartOfDay().minusSeconds(1);
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println("=============");
    }

    @Test
    public void test04(){
        int x = 2<< 28;

        int a= 7;
        String s = Integer.toBinaryString(a);
        int b= 5;
        String s1 = Integer.toBinaryString(b);
        int i = a & b;
        System.out.println("======"+i);
        System.out.println("=====");

        int i1 = a | b;
        System.out.println("=================");
    }

    @Autowired
    private FreeMarkerConfigurer configurer;
    @Test
    public void test05(){

        try {
            System.out.println("==");
            Template template = configurer.getConfiguration().getTemplate("templates/refund.html");
            System.out.println("==");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=====");
    }

    @Test
    public void test06(){
        int a = 120;
        String s = Integer.toBinaryString(a);
        String s1 = Integer.toBinaryString(a << 5);
        System.out.println("=================");
        int i = a >> 5;
        System.out.println("=========");

        String s2 = Integer.toBinaryString(1);
        int i1 = Integer.parseInt(String.valueOf(10000), 2);

        System.out.println("");
    }

    @Test
    public void test(){
        Object[] array = {"a","b","c","d"};
        int i = 0;
        for(int j = array.length - 1; j > i; ++i) {
            Object tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
            System.out.println("");
        }

        System.out.println("================"+array);
    }

    @Test
    public void test07(){

        String s="We are happy.";
        String replace = s.replace(" ", "%20");

        String[] ints = new String[3];


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        int count = list.size()%2 ==0 ? list.size()/2 : list.size()/2+1;
        for (int i = 1; i <= count; i++) {
            List<Integer> subList;
            if (i != count) {
                subList = list.subList(2 * (i-1), 2 * i);
            }else {
                subList = list.subList(2 * (i-1), list.size());
            }
            System.out.println("======="+i);
        }
    }


    @Test
    public void test08() {
        String s = "We are happy.";
//        String replace = s.replace(" ", "%20");
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (' ' == c) {
                sb.append("%20");
            }else {
                sb.append(c);
            }
        }
        String s1 = sb.toString();
        System.out.println("=================================");
    }

    static public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    @Test
    public void test09(){
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(2);
        head.next = head2;
        head2.next = head3;

        LinkedList<Integer> list = new LinkedList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Integer[] arr = new Integer[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.removeLast();
        }

        System.out.println("============"+ arr);


        boolean serialVersionUID = "serialVersionUID".equals("serialVersionUID");
        System.out.println("-=========");

    }


    @Test
    public void test10() {


        String a = "785853249@qq.com";
        String[] split = a.split("-");
        List<String> mailList = Stream.of(split).collect(Collectors.toList());


        List<StudentX> list = new ArrayList<>();
        list.add(new StudentX().setName("李白").setSouce(BigDecimal.valueOf(1.23)));
        list.add(new StudentX().setName("杜甫").setSouce(BigDecimal.valueOf(2.25)));
        long sum = list.stream().mapToLong(s -> s.getSouce().longValue()).sum();
        System.out.println("======================");

    }

    @Test
    public void test11() {


        BigDecimal divide = BigDecimal.valueOf(12365.3).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
        BigDecimal divide2 = BigDecimal.valueOf(12365).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);

//        List<Object> list = new ArrayList<>();

        LinkedList<String> list = new LinkedList<>();
        list.addLast("退费订单明细：");
        list.addLast("\r\n");
        list.addLast("退费订单号");
        list.addLast("|");
        list.addLast("原外部（交易）订单号");
        list.addLast("|");
        list.addLast("场站名称");
        list.addLast("|");
        list.addLast("停车场编号");
        list.addLast("|");
        list.addLast("车牌号");
        list.addLast("|");
        list.addLast("退费金额");
        list.addLast("|");
        list.addLast("退费成功时间");
        list.addLast("|");
        list.addLast("订单类型");
        list.addLast("\r\n");

//        String join = String.join("", list);
        Map<String, Object> map = new HashMap<>();
        List<String> a = (List<String>) map.get("a");
        if (CollectionUtil.isNotEmpty(a)) {
            System.out.println("kongkong");
        }
        System.out.println("======================");

    }


}
