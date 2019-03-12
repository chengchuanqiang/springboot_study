package com.ccq.springbootkafka.algorithm.lanqiaobei01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/********************************
 *** ProblemA: 01串
 *** http://www.ncwu.club/problem.php?id=4493
 ***@Author chengchuanqiang
 ***@Date 2019/3/8 9:49
 ***@Version 1.0.0
 ********************************/
public class ZeroOneString {

    private static void dfs(int n, StringBuffer sbf, int index, List<String> res, Set<String> tmp) {
        if (index == n) {
            if (res.contains(sbf.toString())) {
                return;
            }
            System.out.println(sbf.toString());
            res.add(sbf.toString());
            return;
        }

        for (int i = index; i < n; i++) {
            // 记忆化 剪枝
            if (tmp.contains(sbf.toString())) {
                return;
            }
            tmp.add(sbf.toString());
            sbf.append('0');
            dfs(n, sbf, index + 1, res, tmp);
            sbf.deleteCharAt(sbf.length() - 1);

            sbf.append('1');
            dfs(n, sbf, index + 1, res, tmp);
            sbf.deleteCharAt(sbf.length() - 1);
        }
    }

    public static void main(String[] args) {
//        Long s = System.currentTimeMillis();
        dfs(5, new StringBuffer(), 0, new ArrayList<String>(), new HashSet<String>());
//        Long e = System.currentTimeMillis();
//        System.out.println("time:" + (e - s) / 1000.0);
    }

}
