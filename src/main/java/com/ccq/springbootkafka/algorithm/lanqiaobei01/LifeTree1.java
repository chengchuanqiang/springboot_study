package com.ccq.springbootkafka.algorithm.lanqiaobei01;

import java.util.*;

/**
 * @Description: 生命之树  http://www.ncwu.club/problem.php?cid=1053&pid=1
 * @Author: ChengChuanQiang
 * @Date: 2019/4/11 23:50
 */
public class LifeTree1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        while ((T--) > 0) {
            int n = input.nextInt();
            int m = input.nextInt();
            int d = input.nextInt();

            Map<Integer, List<Integer>> map = new HashMap<>();
            Set<Integer> frogSet = new HashSet<>();
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 0; i < m; i++) {
                frogSet.add(input.nextInt());
            }

            for (int i = 1; i < n; i++) {
                int x = input.nextInt();
                int y = input.nextInt();

                if (map.containsKey(x)) {
                    List<Integer> list = map.get(x);
                    list.add(y);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(y);
                    map.put(x, list);
                }

                if (map.containsKey(y)) {
                    List<Integer> list = map.get(y);
                    list.add(x);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(x);
                    map.put(y, list);
                }

            }

            int num = 0;
            for (int i = 1; i <= n; i++) {
                if (!frogSet.contains(i)) {
                    boolean isOk = true;
                    for (Integer index : frogSet) {
                        if (bfs(map, index, i, dp) > d) {
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

    private static int bfs(Map<Integer, List<Integer>> map, Integer index, int i, int[][] dp) {

        if (dp[i][index] != 0) {
            return dp[i][index];
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(index);
        Map<Integer, Integer> dis = new HashMap<>();
        dis.put(index, 0);
        while (!queue.isEmpty()) {
            Integer top = queue.poll();
            int d = dis.get(top) + 1;
            for (Integer x : map.get(top)) {
                if (!dis.containsKey(x)) {
                    dis.put(x, d);

                    dp[index][x] = d;
                    dp[x][index] = d;

                    queue.add(x);
                }
            }
        }
        return dis.get(i);
    }
}
