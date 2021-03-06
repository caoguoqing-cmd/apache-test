package com.example.apache.pojo;

public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {21,25,36,45,68,15,37,62,59,82,94,58};
        int d= 6;
        radixSort(arr,d);
    }

    private static void radixSort(int[] array,int d) {
        //代表位数对应的数：1,10,100...
        int n = 1;
        //保存每一位排序后的结果用于下一位的排序输入
        int k = 0;
        int length = array.length;
        //排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[][] bucket = new int[10][length];
        //用于保存每个桶里有多少个数字
        int[] order = new int[length];
        while (n < d) {
            //将数组array里的每个数字放在相应的桶里
            for (int num : array) {
                int digit = (num / n) % 10;
                bucket[digit][order[digit]] = num;
                order[digit]++;
            }
            //将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            for (int i = 0; i < length; i++) {
                //这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = bucket[i][j];
                        k++;
                    }
                }
                //将桶里计数器置0，用于下一次位排序
                order[i] = 0;
            }
            n *= 10;
            //将k置0，用于下一轮保存位排序结果
            k = 0;
        }
        System.out.println("============="+bucket);
    }
}
