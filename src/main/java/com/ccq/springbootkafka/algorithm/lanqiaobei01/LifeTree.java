package com.ccq.springbootkafka.algorithm.lanqiaobei01;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Description: 生命之树  http://www.ncwu.club/problem.php?cid=1053&pid=1
 * @Author: ChengChuanQiang
 * @Date: 2019/4/11 23:50
 */
public class LifeTree {


    private static final int INF = 1000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while ((T--) > 0) {
            int n = input.nextInt();
            int m = input.nextInt();
            int d = input.nextInt();

            int[][] map = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        map[i][j] = 0;
                    } else {
                        map[i][j] = INF;
                    }
                }
            }

            Set<Integer> frogSet = new HashSet<>();
            for (int i = 0; i < m; i++) {
                frogSet.add(input.nextInt());
            }

            for (int i = 1; i < n; i++) {
                int x = input.nextInt();
                int y = input.nextInt();

                map[x][y] = 1;
                map[y][x] = 1;
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }

            int num = 0;
            for (int i = 1; i <= n; i++) {
                if (!frogSet.contains(i)) {
                    boolean isOk = true;
                    for (Integer index : frogSet) {
                        if (map[index][i] > d) {
                            isOk = false;
                            break;
                        }
                    }
                    if (isOk) {
                        num++;
                    }

                }
            }
            System.out.println(num);
        }
    }
}
