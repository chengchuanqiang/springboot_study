package com.ccq.springbootkafka.algorithm.sort;

/********************************
 *** 快速排序
 ***@Author chengchuanqiang
 ***@Date 2018/8/8 18:15
 ***@Version 1.0.0
 ********************************/
public class QuickSort {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int p = partition(arr, l, r);
            sort(arr, l, p - 1);
            sort(arr, p + 1, r);
        }
    }

    private static int partition(int[] arr, int l, int r) {

        int v = arr[l];
        // [l+1,j] < v , [j+1,i) > v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i] < v) {
                swap(arr, i, ++j);
            }
        }
        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
