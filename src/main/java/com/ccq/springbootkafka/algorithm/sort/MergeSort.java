package com.ccq.springbootkafka.algorithm.sort;

import org.springframework.util.CollectionUtils;

import java.util.*;

/********************************
 *** 实现K个有序数组的归并操作
 ***@Author chengchuanqiang
 ***@Date 2018/8/9 18:35
 ***@Version 1.0.0
 ********************************/
public class MergeSort {


    private MergeSort() {
    }

    public static void sort(int[] arr, int k) {
        sort(arr, 0, arr.length - 1, k);
    }

    private static void sort(int[] arr, int l, int r, int k) {
        if (r - l + 1 < k) {
            return;
        }
        int step = (r - l + 1) / k;
        sort(arr, l, l + step - 1, k);
        for (int i = 1; i < k - 1; i++) {
            sort(arr, l + step * i, l + step * (i + 1) - 1, k);
        }
        sort(arr, (k - 1) * step + l + 1, r, k);

    }

    // 实现对K个有序数组的归并操作
    // 1、使用小顶堆存储K个数组中最小的值
    // 2、然后遍历小顶堆，每一次取出小顶堆最小的元素，加入到返回的数组中
    // 3、将取出的元素所在数组的下一个元素加到队列中，如果该组的数据已经遍历完成，则找下一个数组，将当前数组遍历到的下一个值加入到小顶堆中
    // 4、当所有的数组中的数据全部遍历完成之后，再将小顶堆中剩余的所有元素依次出队加入到返回的数组中即可
    private static int[] merge(List<List<Integer>> list) {

        if (CollectionUtils.isEmpty(list) || list.size() == 0) {
            return null;
        }

        // 小顶堆
        PriorityQueue<Data> minHeap = new PriorityQueue<>(Comparator.comparingInt(d -> d.val));

        // 当前数组的下标，保存list中第i个位置的list当前遍历到index[i]位置
        int[] index = new int[list.size()];

        int n = 0;
        // 初始化堆最小堆操作
        for (int i = 0; i < list.size(); i++) {
            if (!CollectionUtils.isEmpty(list.get(i)) && list.get(i).size() > 0) {
                n += list.get(i).size();
                minHeap.add(new Data(list.get(i).get(0), i));
                index[i] = 1;
            }
        }
        int[] arr = new int[n];

        int i = 0;
        Data deleteFromQueue;
        Integer currIndex;
        // (n-2k + 1)*(lgk * 2) 其中k为list.size() 也就是初始堆的大小
        while (true) {
            deleteFromQueue = minHeap.poll();
            if (deleteFromQueue == null) {
                continue;
            }
            arr[i++] = deleteFromQueue.val;
            currIndex = deleteFromQueue.arraysIndex;
            if (index[currIndex] < list.get(currIndex).size()) {
                minHeap.add(new Data(list.get(currIndex).get(index[currIndex]), currIndex));
                index[currIndex]++;
            } else {
                int j = 0;
                for (; j < list.size(); j++) {
                    if (index[j] < list.get(j).size()) {
                        break;
                    }
                }
                // 当没有list中所有的数据都被添加完了之后，退出while
                if (j == list.size()) {
                    break;
                }
                minHeap.add(new Data(list.get(j).get(index[j]), j));
                index[j]++;
            }
        }

        // 将堆中剩余的依次添加到arr中
        while (!minHeap.isEmpty()) {
            arr[i++] = minHeap.poll().val;
        }
        return arr;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();

        List<Integer> list1 = Arrays.asList(1, 4, 7, 10, 18, 19);
        List<Integer> list2 = Arrays.asList(2, 5, 8, 11);
        List<Integer> list3 = Arrays.asList(3, 6, 9, 12, 20, 29, 30);
        List<Integer> list4 = new ArrayList<>();

        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        int[] res = merge(list);
        if (res != null) {
            for (int re : res) {
                System.out.print(re + " ");
            }
            System.out.println();
        }
    }

    private static class Data {
        private Integer val;  //值
        private Integer arraysIndex;//所在数组的索引

        public Data(Integer val, Integer arraysIndex) {
            this.val = val;
            this.arraysIndex = arraysIndex;
        }
    }
}
