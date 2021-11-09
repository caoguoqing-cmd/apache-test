package com.example.apache.controller;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caogq
 * @description
 * @create 2021/8/25 16:04
 */
public class Boom {

    private static final int size = 100;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.0001);


    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        System.out.println("=========写入完成");

        for (int i = 0; i < size; i++) {
            if (!bloomFilter.mightContain(i)) {
                System.out.println("逃跑了" + i);
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("=============误伤数："+list.size());


    }
}
