package com.ccq.springbootkafka.algorithm.test20181209.D;

import java.util.Scanner;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/12/9 20:38
 */
public class D {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            int n = input.nextInt();
            int m = input.nextInt();

            int[][] res = getResult(n, m);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (j == 0) {
                        System.out.print(res[i][j]);
                    } else {
                        System.out.print(" " + res[i][j]);
                    }

                }
                System.out.println();
            }
        }
    }

    private static int[][] getResult(int n, int m) {
        int[][] res = new int[n][m];

        int i = n - 1;
        int j = 0;

        int sum = n * m;
        int num = 1;
        while (num <= sum) {
            // 向上
            while (i >= 0 && res[i][j] == 0) {
                res[i--][j] = num++;
            }
            i++;
            j++;

            // 向右
            while (j < m && res[i][j] == 0) {
                res[i][j++] = num++;
            }
            j--;
            i++;

            // 向下
            while (i < n && res[i][j] == 0) {
                res[i++][j] = num++;
            }
            i--;
            j--;

            // 向左
            while (j >= 0 && res[i][j] == 0) {
                res[i][j--] = num++;
            }
            j++;
            i--;
        }
        return res;
    }


}
