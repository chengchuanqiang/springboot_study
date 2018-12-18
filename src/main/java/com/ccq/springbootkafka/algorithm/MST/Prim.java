package com.ccq.springbootkafka.algorithm.MST;

import java.util.Arrays;
import java.util.Random;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/8/14 9:22
 ***@Version 1.0.0
 ********************************/
public class Prim {

    private static int[][] g;
    private static int v;
    private static int e;

    private static boolean[] vis;
    private static int[] from;
    private static int[] low;

    private static void init() {
        v = 10;
        e = v * (v - 1);
        g = new int[v][v];

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                g[i][j] = Integer.MAX_VALUE;
            }
        }
        Random r = new Random();
        int x, y, z;
        for (int i = 0; i < e; i++) {
            x = r.nextInt(v);
            y = r.nextInt(v);
            z = r.nextInt(9) + 1;
            g[x][y] = z;
            g[y][x] = z;
        }
        vis = new boolean[v];
        Arrays.fill(vis, false);

        from = new int[v];
        Arrays.fill(from, -1);

        low = new int[v];
    }

    private static int prim() {
        int res = 0;

        int s = 0;
        vis[s] = true;
        for (int i = 1; i < v; i++) {
            low[i] = g[s][i];
            from[i] = s;
        }

        for (int i = 1; i < v; i++) {
            int min = Integer.MAX_VALUE;
            int temp = 0;
            for (int j = 0; j < v; j++) {
                if (!vis[j] && min > low[j]) {
                    min = low[j];
                    temp = j;
                }
            }
            if (min == Integer.MAX_VALUE) {
                return -1;
            }
            vis[temp] = true;
            res += min;
            for (int j = 0; j < v; j++) {
                if (!vis[j] && low[j] > g[temp][j]) {
                    low[j] = g[temp][j];
                    from[j] = temp;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        init();
        System.out.println(prim());

        for (int i = 1; i < v; i++) {
            System.out.println(from[i] + " ---> " + i + "====" + g[from[i]][i]);
        }
    }

}
