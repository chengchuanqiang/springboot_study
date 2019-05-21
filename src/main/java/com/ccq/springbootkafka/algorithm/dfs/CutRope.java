package com.ccq.springbootkafka.algorithm.dfs;

import java.util.ArrayList;
import java.util.List;

/********************************
 *** 剪绳子  2 3 3 3 3
 ***@Author chengchuanqiang
 ***@Date 2019/4/24 18:17
 ***@Version 1.0.0
 ********************************/
public class CutRope {

    public static void main(String[] args) {
        dfs(10, new ArrayList<>());
        System.out.println(ans);
        System.out.println(ansList);
    }

    private static int ans = 0;
    private static List<Integer> ansList;

    private static void dfs(int n, List<Integer> list) {
        if (n == 0) {
            int newAns = list.stream().reduce(1, (a, b) -> a * b);
            if (newAns > ans) {
                ansList = new ArrayList<>(list);
                ans = newAns;
            }
            return;
        }

        for (int i = 1; i <= n; i++) {
            list.add(i);
            dfs(n - i, list);
            list.remove(list.size() - 1);
        }
    }


}
