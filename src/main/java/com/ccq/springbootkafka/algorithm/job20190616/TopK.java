package com.ccq.springbootkafka.algorithm.job20190616;

import java.util.Arrays;
import java.util.Random;

/**
 * @Description: 一个非常经典的 TOP K 问题，使用快速排序算法进行实现
 * <p>
 * partition倒序整
 * <p>
 * 1、在进行一次partition之后， p = partition(arr, left, right);
 * 2、此时左边元素的个数：len = p - left + 1， 注意此时左边的数包含 p下标的值
 * 如果 len == k, 则返回 arr[p]
 * 如果 len > k, 则说明 在左边的元素中 [left, p] 中求 TOP K
 * 如果 len < k, 则说明 在右边的元素中 [p + 1, right] 中求 TOP (K - len)
 * @Author: ChengChuanQiang
 * @Date: 2019/6/16 8:59
 */
public class TopK {


    public static void main(String[] args) {

        int n = 100;
        int k = 2;
        testTopK(n, k);

    }

    private static void testTopK(int n, int k) {

        Random random = new Random();
        int[] nums = new int[n];

        while (true) {
            for (int i = 0; i < n; i++) {
                nums[i] = random.nextInt(100);
            }
            System.out.println();
            int topK = getTopK(nums, 0, nums.length - 1, k);
            System.out.println("topK:" + topK);
            Arrays.sort(nums);
            System.out.println("nums:" + Arrays.toString(nums));
            if (topK != nums[nums.length - k]) {
                System.err.printf("error");
                break;
            }
            System.out.println();
        }
    }

    /**
     * 求 TOP K的值
     *
     * @param nums  nums
     * @param left  左端点
     * @param right 右端点
     * @param k     k
     * @return res
     */
    private static int getTopK(int[] nums, int left, int right, int k) {

        int p = partitionTopK(nums, left, right);

        int len = p - left + 1;

        if (len == k) {
            return nums[p];
        } else if (len > k) {
            return getTopK(nums, left, p, k);
        } else {
            return getTopK(nums, p + 1, right, k - len);
        }
    }


    /**
     * 划分 nums，选取 第一个元素作为轴，左边都是大于轴，右边都是小于轴的
     *
     * @param nums  nums
     * @param left  左端点
     * @param right 右端点
     * @return 轴的下标
     */
    private static int partitionTopK(int[] nums, int left, int right) {

        int temp = nums[left];
        int i = left;
        int j = right;

        while (i < j) {
            //需要注意的是，这里的j--与i++的顺序不可以调换！ 如果调换了顺序，i会走过头，以至于将后面较大的元素交换到数组开头
            while (j > i && nums[j] <= temp) {
                j--;
            }
            while (i < j && nums[i] >= temp) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, left, j);
        return j;
    }


    private static void testQuickSort() {
        Random random = new Random();

        int[] nums = new int[100];
        int[] copyNums = new int[100];
        while (true) {
            for (int i = 0; i < 100; i++) {
                nums[i] = random.nextInt(1000);
                copyNums[i] = nums[i];
            }
            quickSort(nums, 0, nums.length - 1);
            Arrays.sort(copyNums);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != copyNums[i]) {
                    System.out.println("nums: " + Arrays.toString(nums));
                    System.out.println("copyNums: " + Arrays.toString(copyNums));
                    break;
                }
            }

        }
    }

    private static void quickSort(int[] nums, int left, int right) {

        if (left >= right) {
            return;
        }

        int p = partition(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {

        int temp = nums[left];
        int i = left;
        int j = right;

        while (i < j) {

            //需要注意的是，这里的j--与i++的顺序不可以调换！ 如果调换了顺序，i会走过头，以至于将后面较大的元素交换到数组开头

            while (j > i && nums[j] >= temp) {
                j--;
            }

            while (i < j && nums[i] <= temp) {
                i++;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        swap(nums, left, j);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
