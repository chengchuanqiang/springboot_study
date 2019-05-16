package com.ccq.springbootkafka.algorithm.lintcode;

/********************************
 *** https://www.lintcode.com/problem/paint-house/description
 ***@Author chengchuanqiang
 ***@Date 2019/5/14 10:18
 ***@Version 1.0.0
 ********************************/
public class LintCode515 {

    /**
     * dp[i][0] 第i间房子染红色
     * <p>
     * 0 红 1 蓝 2 绿
     * dp[i][0] = min(dp[i-1][1] + cost[i-1][0], dp[i-1][2] + cost[i-1][0])
     * dp[i][1] = min(dp[i-1][0] + cost[i-1][1], dp[i-1][2] + cost[i-1][1])
     * dp[i][2] = min(dp[i-1][0] + cost[i-1][2], dp[i-1][1] + cost[i-1][2])
     * <p>
     * init
     * dp[0][0] = 0
     * dp[0][1] = 0
     * dp[0][2] = 0
     *
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        int len = costs.length;
        if (len == 0) {
            return 0;
        }

        int[][] dp = new int[len + 1][3];
        dp[0][0] = dp[0][1] = dp[0][2] = 0;
        for (int i = 1; i <= len; i++) {
            dp[i][0] = Math.min(dp[i - 1][1] + costs[i - 1][0], dp[i - 1][2] + costs[i - 1][0]);
            dp[i][1] = Math.min(dp[i - 1][0] + costs[i - 1][1], dp[i - 1][2] + costs[i - 1][1]);
            dp[i][2] = Math.min(dp[i - 1][0] + costs[i - 1][2], dp[i - 1][1] + costs[i - 1][2]);
        }
        return Math.min(dp[len][0], Math.min(dp[len][1], dp[len][2]));
    }

    public static void main(String[] args) {
        LintCode515 test = new LintCode515();
        int[][] costs = {{14, 2, 11}, {11, 14, 5}, {14, 3, 10}};
        System.out.println(test.minCost(costs));
    }
}
