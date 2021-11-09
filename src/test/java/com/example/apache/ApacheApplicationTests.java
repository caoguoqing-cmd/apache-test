package com.example.apache;

import com.alibaba.fastjson.JSON;
import com.example.apache.pojo.ConsumeApplyDto;
import com.example.apache.pojo.ConsumeConfirmDto;
import com.example.apache.pojo.DecryptVehicleDto;
import com.example.apache.pojo.Teach;
import com.example.apache.util.CardAuthUtils;
import com.example.apache.util.PsamUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ApacheApplicationTests {


    @Test
    public void contextLoads() {
        System.out.println("=-======");
    }

    @Test
    public void test01() {
        System.out.println("=-======");
        byte[] content = {'a','b','c'};
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");

            byte[] digest1 = md5.digest(content);
            byte[] digest2 = sha1.digest(content);
            System.out.println("========="+digest1);
            System.out.println("========="+digest2);

            System.out.println("=====================");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test02() throws Exception{
        // 生成RSA公钥/私钥
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
        kpGen.initialize(1024);
        KeyPair kp = kpGen.generateKeyPair();
        PrivateKey sk = kp.getPrivate();
        PublicKey pk = kp.getPublic();
        // 待签名的消息:
        byte[] message = "Hello, World!".getBytes(StandardCharsets.UTF_8);

        // 用私钥签名:
        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(sk);
        s.update(message);
        byte[] signed = s.sign();
        System.out.println(String.format("signature: %x", new BigInteger(1, signed)));

        // 用公钥验证:
        Signature v = Signature.getInstance("SHA1withRSA");
        v.initVerify(pk);
        v.update(message);
        boolean valid = v.verify(signed);
        System.out.println("valid: " + valid);
    }

    @Test
    public void test05(){
        Function<Integer,Integer> xxx = x-> x+10;
        Function<Integer,Integer> www = w-> w*3;

//        Integer y1 = xxx.apply(2);
//        Integer w1 = www.apply(2);

        Integer apply1 = xxx.compose(www).apply(6);
        Integer apply2 = xxx.andThen(www).apply(6);
        Integer apply = xxx.apply(6);
        System.out.println("=====================");
    }

    @Test
    public void test06(){
        Teach teach = new Teach();
        boolean test1 = teach.test("123");
        boolean test2 = teach.test("234");
        System.out.println("=====================");
    }

    @Test
    public void test07(){
        Integer[] arr = {5,4,9,3,7,1,2,6,8};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                Integer y = arr[j];
                if (arr[i] > arr[j]) {
                    arr[j] = arr[i];
                    arr[i]=y;
                }
            }
        }
        System.out.println("==========="+arr);
    }

    @Test
    public void test08(){
        Integer[] arr = {1,2,3,4,5,6,7};

        for (int start = 0,end = arr.length-1;
             start < end;
             start++,end--) {
            Integer temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
        System.out.println("==========="+arr);
    }

    /**
     * 国密消费申请
     */
    @Test
    public void test09(){

        String s = "{\n" +
                "    \"clientId\":\"7bd1793355ad437b84e46627b1ac1e0c\",\n" +
                "    \"proListNo\":\"45010620210721105331ba65496de80d4c26\",\n" +
                "    \"keyVersion\":\"51\",\n" +
                "    \"cardNo\":\"1815000000000001\",\n" +
                "    \"provinceCode\":\"4501\",\n" +
                "    \"offlineSerial\":\"0002\",\n" +
                "    \"money\":\"00000001\",\n" +
                "    \"balance\":\"00000008\",\n" +
                "    \"version\":\"FF\",\n" +
                "    \"algorithm\":\"04\",\n" +
                "    \"rand\":\"B3EBCD2A\",\n" +
                "    \"tradeDate\":\"20180809\",\n" +
                "    \"tradeTime\":\"163415\",\n" +
                "    \"keyId\":\"41\",\n" +
                "    \"terminalNo\":\"450100000001\",\n" +
                "    \"type\":\"02\",\n" +
                "    \"tranMac\":\"2864915AFF8B76176A351103CACC2577\"\n" +
                "}";
        ConsumeApplyDto dto = JSON.parseObject(s, ConsumeApplyDto.class);

        //设置参数加密
        String sortStr = CardAuthUtils.getSortStr(dto);
//        String secret = "39313638383535303833383138363431";
        String secret = "39313638383535303833383138363431";
        String tranMac = CardAuthUtils.getTranMac(sortStr, secret);
        dto.setTranMac(tranMac);
        System.out.println("=========:"+ tranMac);
    }

    /**
     * 国密消费确认
     */
    @Test
    public void test10(){

        String s="{\n" +
                "    \"clientId\":\"7bd1793355ad437b84e46627b1ac1e0c\",\n" +
                "    \"proListNo\":\"45010620210721105331ba65496de80d4c26\",\n" +
                "    \"balance\":\"12125487\",\n" +
                "    \"offlineSerialAfter\":\"0003\",\n" +
                "    \"mac2\":\"8A8EEA7D\",\n" +
                "    \"tac\":\"BB1FCD69\",\n" +
                "    \"code\":\"00\",\n" +
                "    \"tranMac\":\"74A076BA971B2422A76B073BCE7C7F25\"\n" +
                "}";
        ConsumeConfirmDto dto = JSON.parseObject(s, ConsumeConfirmDto.class);


        //设置参数加密
        String sortStr = CardAuthUtils.getSortStr(dto);
//        String secret = "39313638383535303833383138363431";
        String secret = "39313638383535303833383138363431";
        String tranMac = CardAuthUtils.getTranMac(sortStr, secret);
        dto.setTranMac(tranMac);
        System.out.println("=========:"+ tranMac);
    }


    /**
     * 非国密消费申请
     */
    @Test
    public void test11(){

        String s = "{\n" +
                "    \"clientId\":\"7bd1793355ad437b84e46627b1ac1e0c\",\n" +
                "    \"proListNo\":\"450106202108031813440000000000000001\",\n" +
                "    \"keyVersion\":\"51\",\n" +
                "    \"cardNo\":\"1837220110878821\",\n" +
                "    \"provinceCode\":\"4501\",\n" +
                "    \"offlineSerial\":\"137F\",\n" +
                "    \"money\":\"00000001\",\n" +
                "    \"balance\":\"000003f1\",\n" +
                "    \"version\":\"01\",\n" +
                "    \"algorithm\":\"00\",\n" +
                "    \"rand\":\"EF17309A\",\n" +
                "    \"tradeDate\":\"20210803\",\n" +
                "    \"tradeTime\":\"181344\",\n" +
                "    \"keyId\":\"01\",\n" +
                "    \"terminalNo\":\"450100000001\",\n" +
                "    \"type\":\"01\",\n" +
                "    \"tranMac\":\"C742B5422D10F80A25221A90EDE2CF0A\"\n" +
                "}";
        ConsumeApplyDto dto = JSON.parseObject(s, ConsumeApplyDto.class);


        //设置参数加密
        String sortStr = CardAuthUtils.getSortStr(dto);
//        String secret = "39313638383535303833383138363431";
        String secret = "39313638383535303833383138363431";
        String tranMac = CardAuthUtils.getTranMac(sortStr, secret);
        dto.setTranMac(tranMac);
        System.out.println("=========:"+ tranMac);
    }


    /**
     * 非国密消费确认
     */
    @Test
    public void test13(){

        String s="{\n" +
                "    \"clientId\":\"7bd1793355ad437b84e46627b1ac1e0c\",\n" +
                "    \"proListNo\":\"45010620210721140121ba65496de80d4c26\",\n" +
                "    \"balance\":\"12154875\",\n" +
                "    \"offlineSerialAfter\":\"0003\",\n" +
                "    \"mac2\":\"8A8EEA7D\",\n" +
                "    \"tac\":\"BB1FCD69\",\n" +
                "    \"code\":\"00\",\n" +
                "    \"tranMac\":\"38862FAE9F219AF215ED07D4E3826FDB\"\n" +
                "}";
        ConsumeConfirmDto dto = JSON.parseObject(s, ConsumeConfirmDto.class);


        //设置参数加密
        String sortStr = CardAuthUtils.getSortStr(dto);
//        String secret = "39313638383535303833383138363431";
        String secret = "39313638383535303833383138363431";
        String tranMac = CardAuthUtils.getTranMac(sortStr, secret);
        dto.setTranMac(tranMac);
        System.out.println("=========:"+ tranMac);
    }


    /**
     * 信息解密
     */
    @Test
    public void te(){
        String s="{\"tranMac\":\"4CE5B81B5E77BFDF478E643B6FE1C41C\",\n" +
                "\"clientId\":\"7bd1793355ad437b84e46627b1ac1e0c\",\n" +
                "\"keyVersion\":\"51\",\n" +
                "\"data\":\"01DCDA72B2AA50A3D4E821E88A972C090BFB1E596F8B4ECDBE514F50749D5366\",\n" +
                "\"obuSerial\":\"1101120175011168\",\n" +
                "\"provinceCode\":\"4501\",\n" +
                "\"type\":\"00\",\n" +
                "\"cardId\":\"1201230005009542\",\n" +
                "\"version\":\"41\"\n" +
                "}";
        DecryptVehicleDto dto = JSON.parseObject(s, DecryptVehicleDto.class);
        //设置参数加密
        String sortStr = CardAuthUtils.getSortStr(dto);
//        String secret = "39313638383535303833383138363431";
        String secret = "39313638383535303833383138363431";
        String tranMac = CardAuthUtils.getTranMac(sortStr, secret);
        System.out.println("=========:"+ tranMac);



    }


    @Test
    public void test14(){
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("li","李白");
        treeMap.put("sb","苏轼");
        treeMap.put("du","杜甫");
        treeMap.put("bai","白居易");
        treeMap.put("xin","辛弃疾");
        treeMap.put("su","苏轼");

        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            System.out.println("=========" + entry.toString());
        }
    }

    @Test
    public void test15(){
        int[] arr = new int[5];

        System.out.println("==========" + arr);

        int[][] ints = new int[5][8];

        System.out.println("=========" + ints);

    }

    @Test
    public void test16(){
        String st= "abcdef";
        String s = StringUtils.uncapitalize(st);
        System.out.println("==============="+s);
    }

    @Test
    public void test17(){
        String st= "abcdef";
        byte[] bytes = st.getBytes(StandardCharsets.UTF_8);
        System.out.println("==============="+bytes);
    }

    @Test
    public void test18(){
        StringJoiner joiner = new StringJoiner(":","[","]");
        System.out.println("-------------------->"+joiner.toString());
        joiner.add("beautiful");
        StringJoiner girl = new StringJoiner("_girl");
        StringJoiner merge = joiner.merge(girl);
        System.out.println("==============="+merge.toString());
    }

    @Test
    public void test19(){
        List<String> strings = Arrays.asList("s", "a", "d", "t");
        List<Object> objects = new ArrayList<>();
        strings.forEach(s -> {
            boolean d = s.equals("d");
            if (d) {
                objects.add(s);
            }
        });
        System.out.println("===============");
    }

    @Test
    public void test20(){
        LocalDateTime now = LocalDateTime.now();

        System.out.println("==============="+now);
    }

    public final static int HASH_PREFIX = 18;
    @Test
    public void test21(){
        String str = "2022CA06F8F6587BD2B9F04157323130560000000000000100000000000000000080000000000000";
        str = str.substring(HASH_PREFIX);
        final List<Byte> byteList = PsamUtil.parseHexByteList(str);
        String plateNo = PsamUtil.getPlainTextData(byteList, 0, 12);
        String plateColor = PsamUtil.getPlainTextData(byteList,12,14);
        String vehicleType = PsamUtil.getPlainTextData(byteList,14,15);
        System.out.println("==========================");
    }
}














