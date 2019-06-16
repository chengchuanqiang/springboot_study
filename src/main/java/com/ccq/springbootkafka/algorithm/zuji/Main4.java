package com.ccq.springbootkafka.algorithm.zuji;

import java.util.Scanner;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/4/14 11:29
 */
public class Main4 {

    private static int n;
    private static int ans = Integer.MAX_VALUE;
    private static boolean flag[];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        flag = new boolean[n + 1];
        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = input.nextInt();
            }
        }
        ans = Integer.MAX_VALUE;
        dfs(1, 1, 0, map);
        System.out.println(ans);
    }

    private static void dfs(int start, int num, int sum, int[][] map) {
        if (num == n) {
            ans = Math.min(ans, sum + map[start][1]);
            return;
        }
        for (int i = 2; i <= n; i++) {
            if (!flag[i]) {
                flag[i] = true;
                dfs(i, num + 1, sum + map[start][i], map);
                flag[i] = false;
            }
        }
    }
}
