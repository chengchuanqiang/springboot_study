package com.ccq.springbootkafka.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/8/8 18:15
 ***@Version 1.0.0
 ********************************/
public class Test {

    public static void main(String[] args) {

        int n = 1000000;
        int m = 10;

        int[] arr1 = RandomUtils.getRandomNum(n);
        int[] arr2 = Arrays.copyOf(arr1, n);
        System.out.println("RandomNum : ");
        Long s = System.currentTimeMillis();
        QuickSort.sort1(arr1);
        Long e = System.currentTimeMillis();
        System.out.println((e - s) / 1000.0 + "s");
        s = System.currentTimeMillis();
        QuickSort.sort2(arr2);
        e = System.currentTimeMillis();
        System.out.println((e - s) / 1000.0 + "s");

        arr1 = RandomUtils.getPartSortNum(n, m);
        arr2 = Arrays.copyOf(arr1, n);
        System.out.println("PartSortNum : ");
        s = System.currentTimeMillis();
        QuickSort.sort1(arr1);
        e = System.currentTimeMillis();
        System.out.println((e - s) / 1000.0 + "s");
        s = System.currentTimeMillis();
        QuickSort.sort2(arr2);
        e = System.currentTimeMillis();
        System.out.println((e - s) / 1000.0 + "s");

        arr1 = RandomUtils.getSortNum(n);
        arr2 = Arrays.copyOf(arr1, n);
        System.out.println("SortNum : ");
        s = System.currentTimeMillis();
        QuickSort.sort1(arr1);
        e = System.currentTimeMillis();
        System.out.println((e - s) / 1000.0 + "s");
        s = System.currentTimeMillis();
        QuickSort.sort2(arr2);
        e = System.currentTimeMillis();
        System.out.println((e - s) / 1000.0 + "s");

        arr1 = RandomUtils.getRepeatNum(n, m);
        arr2 = Arrays.copyOf(arr1, n);
        System.out.println("RepeatNum : ");
        s = System.currentTimeMillis();
        QuickSort.sort1(arr1);
        e = System.currentTimeMillis();
        System.out.println((e - s) / 1000.0 + "s");
        s = System.currentTimeMillis();
        QuickSort.sort2(arr2);
        e = System.currentTimeMillis();
        System.out.println((e - s) / 1000.0 + "s");

    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
