package com.ccq.springbootkafka.algorithm.sort;

/********************************
 *** 快速排序
 ***@Author chengchuanqiang
 ***@Date 2018/8/8 18:15
 ***@Version 1.0.0
 ********************************/
public class QuickSort {

    private QuickSort() {
    }

    public static void sort1(int[] arr) {
        sort1(arr, 0, arr.length - 1);
    }

    public static void sort1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition1(arr, l, r);
        sort1(arr, l, p - 1);
        sort1(arr, p + 1, r);
    }

    private static int partition1(int[] arr, int l, int r) {

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

    public static void sort2(int[] arr) {
        sort2(arr, 0, arr.length - 1);
    }

    public static void sort2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition2(arr, l, r);
        sort2(arr, l, p - 1);
        sort2(arr, p + 1, r);
    }

    private static int partition2(int[] arr, int l, int r) {
        int v = arr[l];

        // [l+1, i) < v , (j,r] > v
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= r && arr[i] < v) {
                i++;
            }
            while (j >= l + 1 && arr[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i++, j--);
        }
        swap(arr, l, j);
        //System.out.println("jjj==" + j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
