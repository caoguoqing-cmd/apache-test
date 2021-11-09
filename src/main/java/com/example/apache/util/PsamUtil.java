package com.example.apache.util;

import cn.hutool.core.util.StrUtil;
import sun.nio.cs.ext.GBK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * PSAM卡复合消费工具
 * @author 张晖
 */
public class PsamUtil {

    /**
     * 把Hex字符串解析为byte列表
     * @param binary  Hex字符串
     * @return byte列表
     */
    public static List<Byte> parseHexByteList(String binary) {
        String[] strArr = StrUtil.split(binary, 2);
        return Arrays.stream(strArr).map(s -> {
            int i = Integer.parseInt(s, 16);
            return ((byte) i);
        }).collect(Collectors.toList());
    }

    /**
     * 获取明文数据
     * @param byteList 从Hex字符串解析得到的字节列表
     * @param begin 开始的下标
     * @param end 结束的下标
     * @return 明文数据
     */
    public static String getPlainTextData(List<Byte> byteList, int begin, int end) {
        int size = byteList.size();
        if (size<=begin || size<=end) {
            return "";
        }
        List<Byte> plateNoList = byteList.subList(begin, end);
        int length = end - begin;
        length = length == 0 ? 1 : length;
        byte[] plateNoArr = new byte[length];
        IntStream.range(0, length).forEach(s -> {
            plateNoArr[s] = plateNoList.get(s);
        });
        return new String(plateNoArr, new GBK()).trim();
    }
}
