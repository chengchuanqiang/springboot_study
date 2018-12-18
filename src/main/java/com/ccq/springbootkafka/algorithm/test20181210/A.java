package com.ccq.springbootkafka.algorithm.test20181210;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/10 18:24
 ***@Version 1.0.0
 ********************************/
public class A {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), n);

        StringBuilder sbf;
        for (List<Integer> list : res) {
            sbf = new StringBuilder();
            sbf.append(n).append("=");
            for (Integer i : list) {
                sbf.append(i).append("+");
            }
            System.out.println(sbf.substring(0, sbf.length() - 1));
        }
        input.close();
    }

    public static void dfs(List<List<Integer>> res, List<Integer> curr, int sum) {
//        System.out.print(curr);
//        System.out.println("  " + sum);
        if (sum == 0) {
            List<Integer> newList = new ArrayList<>(curr);
            Collections.sort(newList);
            if (!res.contains(newList)) {
                res.add(newList);
            }
            return;
        }

        for (int i = 1; i <= sum; i++) {
            curr.add(i);
            dfs(res, curr, sum - i);
            curr.remove(curr.size() - 1);
        }

    }
}
