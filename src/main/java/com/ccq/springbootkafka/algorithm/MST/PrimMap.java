package com.ccq.springbootkafka.algorithm.MST;

import java.util.Arrays;

/********************************
 *** 最小生成树 邻接矩阵
 ***@Author chengchuanqiang
 ***@Date 2019/3/27 12:47
 ***@Version 1.0.0
 ********************************/
public class PrimMap {

    private static int prim(int[][] map) {
        int n = map.length;
        boolean[] vis = new boolean[n];
        int[] d = new int[n];
        int[] pre = new int[n];
        Arrays.fill(vis, false);
        Arrays.fill(d, -1);
        Arrays.fill(pre, -1);

        int result = 0;

        // init
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                d[i] = 0;
            } else {
                d[i] = map[0][i];
                pre[i] = 0;
            }
        }

        vis[0] = true;
        for (int i = 1; i < n; i++) {

            int min = Integer.MAX_VALUE;
            int currIndex = -1;
            for (int j = 0; j < n; j++) {
                if (!vis[j] && d[j] < min) {
                    min = d[j];
                    currIndex = j;
                }
            }

            // 不连通
            if (min == Integer.MAX_VALUE) {
                return -1;
            }

            vis[currIndex] = true;
            result += min;

            for (int j = 0; j < n; j++) {
                if (!vis[j] && d[j] > map[currIndex][j]) {
                    d[j] = map[currIndex][j];
                    pre[j] = currIndex;
                }
            }
        }

        System.out.println("========print path=======");
        for (int i = 0; i < n; i++) {
            if (pre[i] != -1) {
                System.out.println(i + " <<==>>" + pre[i] + " = " + map[i][pre[i]]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // 初始化
        int[][] map = new int[6][6];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        map[0][1] = 6;
        map[1][0] = 6;
        map[0][2] = 1;
        map[2][0] = 1;
        map[0][3] = 5;
        map[3][0] = 5;

        map[1][2] = 5;
        map[2][1] = 5;
        map[1][4] = 3;
        map[4][1] = 3;

        map[2][3] = 5;
        map[3][2] = 5;
        map[2][4] = 6;
        map[4][2] = 6;
        map[2][5] = 4;
        map[5][2] = 4;

        map[3][5] = 2;
        map[5][3] = 2;

        map[4][5] = 6;
        map[5][4] = 6;
        System.out.println(prim(map));

    }

}
