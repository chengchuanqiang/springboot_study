package com.ccq.springbootkafka.algorithm.test20181210;

import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/10 18:19
 ***@Version 1.0.0
 ********************************/
public class C {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = 1;
        while (input.hasNext()) {
            int n = input.nextInt();

            int[][] res = getResult(n);
            System.out.println("Case " + x++ + ":");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
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

    private static int[][] getResult(int n) {
        int[][] res = new int[n][n];

        int i = 0;
        int j = n - 1;

        int sum = n * n;
        int num = 1;
        while (num <= sum) {

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

            // 向上
            while (i >= 0 && res[i][j] == 0) {
                res[i--][j] = num++;
            }
            i++;
            j++;

            // 向右
            while (j < n && res[i][j] == 0) {
                res[i][j++] = num++;
            }
            j--;
            i++;


        }
        return res;
    }


}
