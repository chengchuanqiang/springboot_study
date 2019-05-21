package com.ccq.springbootkafka.algorithm.yjx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/3/16 11:39
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        while (n-- != 0) {
            int m = input.nextInt();
            int[] num = new int[m + 1];
            int[] res = new int[m + 1];
            int minIndex = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                res[i] = 1;
                num[i] = input.nextInt();
                if (min > num[i]) {
                    min = num[i];
                    minIndex = i;
                }
            }
            for (int i = minIndex; i < m; i++) {
                if (i + 1 < m && num[i + 1] > num[i]) {
                    res[i + 1] = res[i] + 1;
                }

                // 特判
                if (i + 1 == m - 1 && num[i + 1] > num[0] && res[i + 1] < res[0] + 1) {
                    res[i + 1] = res[0] + 1;
                }
            }
            if (minIndex - 1 > 0) {
                for (int i = minIndex; i >= 0; i--) {
                    if (i - 1 >= 0 && num[i - 1] > num[i]) {
                        res[i - 1] = res[i] + 1;
                    }
                    // 特判
                    if (i - 1 == 0 && num[i - 1] > num[m - 1] && res[i - 1] < res[m - 1] + 1) {
                        res[i - 1] = res[m - 1] + 1;
                    }
                }
            }
            long sum = 0;
            for (int i = 0; i < m; i++) {
                sum += res[i];
            }
            System.out.println(sum);
        }
    }
}
