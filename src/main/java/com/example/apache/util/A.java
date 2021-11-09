package com.example.apache.util;

import cn.hutool.core.util.StrUtil;
import sun.nio.cs.ext.GBK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class A {


    public static String getPlainTextData(String binary, int begin, int end) {
        String[] strArr = StrUtil.split(binary, 2);
        List<Byte> byteList = Arrays.stream(strArr).map(s -> {
            int i = Integer.parseInt(s, 16);
            return ((byte) i);
        }).collect(Collectors.toList());
        List<Byte> plateNoList = byteList.subList(begin, end);
        int length = end - begin;
        length = length == 0 ? 1 : length;
        byte[] plateNoArr = new byte[length];
        IntStream.range(0, length).forEach(s -> {
            plateNoArr[s] = plateNoList.get(s);
        });
        return new String(plateNoArr, new GBK()).trim();
    }

    public static void main(String[] args) {

        String all = "4A9513979A7A6AFA63B015337E71DFF40D7833977AEB5C33AA3983C8DC19916B";
        String str = all.substring(18);

        String plateNo = getPlainTextData(str, 0, 12);
        String plateColor = getPlainTextData(str, 12, 14);
        String vehicleType = getPlainTextData(str, 14, 15);
        DecryptVehicleInBlackDTO result = new DecryptVehicleInBlackDTO();
        result/*.setAxleCount(axleCount).setAxleLength(axleLength).setLength(length)*/.setPlateColor(plateColor).setCarType(vehicleType)
//                .setDesc(desc).setEngine(engine).setHeight(height)
//                .setWheelCount(wheelCount).setWidth(width).setOwnerType(userType).setWeight(weightPerSeat)
                .setPlateNo(plateNo);

//        System.out.println("input  =  B9F04157323130560000000000000100000000000000000080000000000000");
        System.out.println(" = " + result);
    }
}
