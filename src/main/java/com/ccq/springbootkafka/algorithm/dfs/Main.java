package com.ccq.springbootkafka.algorithm.dfs;

import java.util.*;

/********************************
 *** 2019网易java开发岗位题目
 ***@Author chengchuanqiang
 ***@Date 2018/9/28 19:16
 ***@Version 1.0.0
 ********************************/
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            int sum = 0;
            int[] level = new int[n];
            for (int i = 0; i < n; i++) {
                level[i] = input.nextInt();
                sum += level[i];
            }
            Arrays.sort(level);
            List<Integer> currList = new ArrayList<>();
            List<List<Integer>> resList = new ArrayList<>();
            dfs(level, sum / 2, 0, currList, resList);
            System.out.println(resList.size());
            // 打印所有符合条件的结果
            resList.forEach(System.out::print);
        }
    }

    private static void dfs(int[] level, int sum, int i, List<Integer> currList, List<List<Integer>> resList) {
        int sumList = sumList(currList);
        if (sumList > sum) {
            if (currList.get(0) > sumList - sum) {
                resList.add(new ArrayList<>(currList));
            } else {
                return;
            }
        }
        for (int j = i; j < level.length; j++) {
            currList.add(level[j]);
            dfs(level, sum, j + 1, currList, resList);
            currList.remove(currList.size() - 1);
        }

    }

    private static int sumList(List<Integer> currList) {
        return currList.stream().reduce(0, Integer::sum);
    }

}
