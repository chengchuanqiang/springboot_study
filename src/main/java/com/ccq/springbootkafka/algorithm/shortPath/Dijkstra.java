package com.ccq.springbootkafka.algorithm.shortPath;

import java.util.Arrays;

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
        int[][] map = new int[5][5];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 9999;
            }
        }
        map[0][1] = 5;
        map[0][2] = 8;
        map[1][2] = 1;
        map[1][3] = 3;
        map[1][4] = 2;
        map[3][4] = 7;

        map[1][0] = 5;
        map[2][0] = 8;
        map[2][1] = 1;
        map[3][1] = 3;
        map[4][1] = 2;
        map[4][3] = 7;

        dij(map);
    }

}
