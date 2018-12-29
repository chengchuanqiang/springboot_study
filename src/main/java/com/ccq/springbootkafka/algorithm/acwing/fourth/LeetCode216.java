package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/28 18:22
 ***@Version 1.0.0
 ********************************/
public class LeetCode216 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), n, k, 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, ArrayList<Integer> curr, int n, int k, int index) {

        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = index; i < 10; i++) {
            if (n < i) {
                return;
            }
            curr.add(i);
            dfs(res, curr, n - i, k - 1, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode216 test = new LeetCode216();

        System.out.println(test.combinationSum3(3, 7));
        System.out.println(test.combinationSum3(3, 9));
    }
}
