package com.example.apache.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangKai
 * @version 1.0
 * @date 2020/11/19
 * @description 卡授权工具类
 */
@Slf4j
public class CardAuthUtils {

    /**
     * 按照加密规则拼接字符串
     * @param object 校验参数实体类
     * @param <T> 泛型
     * @return 拼接完成以后的字符串
     */
    public static <T> String getSortStr(T object) {

        //排序map
        TreeMap<String, Object> treeMap = new TreeMap<>();

        //获取对象的属性和值
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            //如果碰到tranMac参数那么跳过
            if ("tranMac".equals(field.getName())) {
                continue;
            }
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }

            if ("cardId".equals(field.getName())) {
                continue;
            }

            try {
                treeMap.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                //打印异常
                log.error("按照加密规则拼接字符串: 获取对象属性失败 errorMsg: {} time: {}",
                        e.getMessage(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        }

        //临时tranMac
        StringBuilder tempTranMac = new StringBuilder("");

        //遍历treeMap进行字符串拼接
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            tempTranMac.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        String tranMac = tempTranMac.toString();

        //返回待加密数据D
        return tranMac.substring(0, tranMac.length()-1);
    }

    /**
     * 参数加密
     * @param sortStr 待加密数据Dn
     * @return 加密完成的数据
     */
    public static String getTranMac (String sortStr, String secret) {

        //1.数据填充
        //默认分组数组Dn
        byte[] groupArr = new byte[16];

        //待加密数据转byte数组
        byte[] tranMacBytes = sortStr.getBytes(StandardCharsets.UTF_8);

        //设置扩容数组
        byte[] bytes =
                Arrays.copyOf(tranMacBytes, tranMacBytes.length+(16-tranMacBytes.length%16));

        //获取分组个数, 每组16个字节
        int groupNum = (int) Math.ceil(tranMacBytes.length / 16d);

        //封装Dn数组的集合
        List<byte[]> dataDList = new ArrayList<>(groupNum);

        //封装On数组的集合
        List<byte[]> dataOList = new ArrayList<>(groupNum);

        //数据Dn填充
        for (int j=0; j<bytes.length; j++) {

            //增加填充数组
            if ((j%16) == 0 && j>0) {
                dataDList.add(groupArr);
                groupArr = new byte[16];
            }

            //遍历填充
            groupArr[j%16] = bytes[j];

            //保证最后一个Dn被录入到list集合中
            if (j == bytes.length-1) {
                dataDList.add(groupArr);
            }
        }

        //2.循环异或获取On
        //添加第一个O0 (第一个O0和D0相等)
        dataOList.add(dataDList.get(0));
        for (int i=1; i<dataDList.size(); i++) {
            //获取O1 O2 ... O3
            dataOList.add(getDataO(dataDList.get(i), dataOList.get(i-1)));
        }

        //3.将On使用16进制字符串秘钥K0进行SM4算法ECB模式加密
        String tranMac64 = encryptData_ECB(dataOList.get(groupNum - 1), secret);

        //返回最终加密结果
        return tranMac64.substring(0, 32).toUpperCase();
    }

    /**
     * 循环异或
     * @param dataD 待加密dataD1
     * @param dataO 循环异或后获取的上一个dataO0
     * @return 新的dataO1
     */
    public static byte[] getDataO(byte[] dataD, byte[] dataO) {

        //初始化新的DataO
        byte[] newDataO = new byte[16];

        //按位异或
        for (int i=0; i<dataD.length; i++) {
            newDataO[i] = (byte) (dataD[i] ^ dataO[i]);
        }

        return newDataO;
    }

    /**
     * SM4算法
     * @param dataO
     * @return 加密以后的密文
     */
    public static String encryptData_ECB(byte[] dataO, String secret) {
        try {
            System.out.println("dataO = " + dataO);
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            keyBytes = Util.hexStringToBytes(secret);
            SM4 sm4 = new SM4();
            sm4.sm4SetkeyEnc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4CryptEcb(ctx, dataO);
            return Util.byteToHex(encrypted);
        } catch (Exception e) {
            log.error("加密失败 errorMsg: {} time: {}",
                    e.getMessage(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return "";
        }
    }
}
