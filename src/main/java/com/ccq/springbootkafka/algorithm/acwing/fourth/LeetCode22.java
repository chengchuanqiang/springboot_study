package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/29 9:50
 ***@Version 1.0.0
 ********************************/
public class LeetCode22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n <= 0) {
            return res;
        }
//        dfs(res, new StringBuilder(), n, 0, new int[2]);
        dfs2(res, "", n,n);
        return res;
    }

    /**
     * 深度优先遍历
     *
     * @param res   结果
     * @param curr  当前值
     * @param n     括号对数
     * @param index 当前深度
     * @param sum   左右括号个数 sum[0] 左括号， sum[1] 右括号
     */
    private void dfs(List<String> res, StringBuilder curr, int n, int index, int[] sum) {
        if (index == n * 2) {
            res.add(curr.toString());
            return;
        }

        for (int i = index; i < n * 2; i++) {
            // 剪枝：结果长度要和当前遍历的i值一样，才可以保证结果长度一定是2n
            if (curr.length() != i) {
                return;
            }
            // 剪枝：右括号小于左括号，左括号个数不能大于n
            if (sum[1] > sum[0] || sum[0] > n) {
                return;
            }

            // 左括号个数为n时，只能加右括号
            if (sum[0] == n) {
                sum[1]++;
                curr.append(")");
                dfs(res, curr, n, i + 1, sum);
                curr.deleteCharAt(curr.length() - 1);
                sum[1]--;
            } else {
                curr.append("(");
                sum[0]++;
                dfs(res, curr, n, i + 1, sum);
                curr.deleteCharAt(curr.length() - 1);
                sum[0]--;

                sum[1]++;
                curr.append(")");
                dfs(res, curr, n, i + 1, sum);
                curr.deleteCharAt(curr.length() - 1);
                sum[1]--;
            }
        }
    }

    private void dfs2(List<String> res, String curr, int left, int right) {

        if (left == 0 && right == 0) {
            res.add(curr);
            return;
        }

        if (left > 0) {
            dfs2(res, curr + "(", left - 1, right);
        }

        if (right > left) {
            dfs2(res, curr + ")", left, right - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode22 test = new LeetCode22();
        System.out.println(test.generateParenthesis(3));
    }
}
