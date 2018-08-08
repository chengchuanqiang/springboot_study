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

        int N = 10;

        Random random = new Random();
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = random.nextInt(N);
            arr2[i] = arr1[i];
        }

        QuickSort.sort1(arr1);
        print(arr1);

        QuickSort.sort2(arr1);
        print(arr1);

    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
