package com.ccq.springbootkafka.algorithm.shortPath;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Description: 单源最短路，没有负权路径
 * @Author: ChengChuanQiang
 * @Date: 2019/4/1 0:26
 */
public class Dijkstra {

    private static int dij(int[][] map) {
        int n = map.length;
        int[] d = new int[n];
        boolean[] vis = new boolean[n];
        Arrays.fill(vis, false);
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                d[i] = 0;
            } else {
                d[i] = map[0][i];
            }
        }
        vis[0] = true;
        for (int i = 1; i < n; i++) {
            int min = 9999;
            int minIndex = -1;
            for (int j = 0; j < n; j++) {
                if (!vis[j] && d[j] < min) {
                    min = d[j];
                    minIndex = j;
                }
            }
            // 不连通
            if (minIndex == -1) {
                return -1;
            }
            vis[minIndex] = true;

            for (int j = 0; j < n; j++) {
                if (d[j] > map[minIndex][j] + min) {
                    System.out.println(d[j] + " ==" + (map[minIndex][j] + min));
                    d[j] = map[minIndex][j] + min;
                }
            }

        }
        return d[n - 1];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 9999;
            }
        }

        while ((m--) > 0) {
            int s = input.nextInt();
            int e = input.nextInt();
            int v = input.nextInt();
            map[s][e] = v;
            map[e][s] = v;
        }

        dij(map);
    }

}
