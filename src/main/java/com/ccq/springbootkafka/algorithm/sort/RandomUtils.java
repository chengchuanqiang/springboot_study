package com.ccq.springbootkafka.algorithm.sort;

import java.util.Random;

/********************************
 *** 生成随机数数组
 ***@Author chengchuanqiang
 ***@Date 2018/8/9 9:19
 ***@Version 1.0.0
 ********************************/
public class RandomUtils {

    private RandomUtils() {
    }

    public static int[] getRandomNum(int n) {
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    public static int[] getSortNum(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int[] getRepeatNum(int n, int m) {
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(m);
        }
        return arr;
    }

    public static int[] getPartSortNum(int n, int m) {
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < m; i++) {
            swap(arr, random.nextInt(n), random.nextInt(n));
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
