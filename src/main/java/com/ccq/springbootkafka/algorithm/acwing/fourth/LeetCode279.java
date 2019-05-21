package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: https://leetcode.com/problems/perfect-squares/
 * @Author: ChengChuanQiang
 * @Date: 2019/5/20 7:37
 */
public class LeetCode279 {

    public static void main(String[] args) {
        LeetCode279 test = new LeetCode279();
        System.out.println(test.numSquares(13));
        System.out.println(test.numSquares2(5001));
        System.out.println(test.numSquares3(5001));
    }

    /**
     * dp[i] 表示 i 最少被分成几个完全平方数之和
     * dp[i] = min{dp[i-j*j] + 1}  j*j = [0, i]
     *
     * @param n n
     * @return result
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        int[] pre = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; j * j <= i; j++) {
                if (dp[i] > dp[i - j * j] + 1) {
                    dp[i] = dp[i - j * j] + 1;
                    pre[i] = j * j;
                }

            }
        }
        int index = n;
        int p = pre[index];
        while (p != 0) {
            System.out.print(p + " ");
            index = index - p;
            p = pre[index];
        }
        System.out.println();
        return dp[n];
    }

    public int numSquares3(int n) {
        int[] dp = new int[n + 1];
        Map<Integer, List<Integer>> preMap = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            preMap.put(i, new ArrayList<>());
        }
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 0; j * j <= i; j++) {
                if (dp[i] > dp[i - j * j] + 1) {
                    dp[i] = dp[i - j * j] + 1;
                    List<Integer> pre = new ArrayList<>();
                    pre.add(j * j);
                    preMap.put(i, pre);
                } else if (dp[i] == dp[i - j * j] + 1) {
                    preMap.get(i).add(j * j);
                }
            }
        }
        dfs(n, new ArrayList<>(), preMap, preMap.get(n));
        System.out.println(res);
        return dp[n];
    }

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 输出所有的方案，从小到大
     *
     * @param n      n
     * @param curr   当前值
     * @param preMap 前缀Map
     * @param pres   前缀list
     */
    private void dfs(int n, List<Integer> curr, Map<Integer, List<Integer>> preMap, List<Integer> pres) {

        if (n == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (Integer pre : pres) {
            if (curr.size() > 0 && curr.get(curr.size() - 1) > pre) {
                continue;
            }
            curr.add(pre);
            dfs(n - pre, curr, preMap, preMap.get(n - pre));
            curr.remove(curr.size() - 1);
        }
    }

}
