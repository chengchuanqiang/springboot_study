package com.ccq.springbootkafka.algorithm.zuji;

import java.util.*;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/4/13 20:28
 */
public class Main {
    public static int resNum = 0;
    public static int len = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        List<String> strs = new ArrayList<>();
        strs.sort(Comparator.naturalOrder());
        for (int i = 0; i < m; i++) {
            strs.add(input.next());
        }
        String t = input.next();
        input.close();

        resNum = 0;
        len = t.length();
        dfs(t, strs, 0);
        System.out.println(resNum);
    }

    private static void dfs(String t, List<String> strs, int num) {

        if (num > resNum) {
            resNum = num;
        }

        // 剪枝
        if (num + t.length() <= resNum || num + t.length() / strs.get(0).length() <= resNum
                || len == resNum || t.length() <= 0) {
            return;
        }

        for (String str : strs) {
            int index = t.indexOf(str);
            if (index != -1) {
                num++;
                dfs(t.substring(index + str.length()), strs, num);
                num--;
            }
        }
    }

}

