package com.ccq.springbootkafka.algorithm.jisuanke.y2019_1;

import java.util.*;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/5/25 18:54
 */
public class A {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < n; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            if (map.containsKey(x)) {
                map.get(x).add(y);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(y);
                map.put(x, list);
            }
        }
        Map<Integer, List<Integer>> res = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue((m1, m2) -> m2.size() - m1.size())).forEach(e -> res.put(e.getKey(), e.getValue()));

        boolean[] flag = new boolean[n + 1];

        if (n == 1 || n == 2) {
            System.out.println(0);
        } else if (n == 3) {
            System.out.println(1);
        } else {
            if (res.size() == 1) {
                System.out.println(n - 1);
            } else {
                int result = 0;
                List<Integer> remove = new ArrayList<>();
                for (Map.Entry<Integer, List<Integer>> entry : res.entrySet()) {
                    if (remove.size() >= 2) {
                        result++;
                        Integer key = entry.getKey();
                        flag[key] = true;
                        List<Integer> value = entry.getValue();
                        for (Integer v : value) {
                            flag[v] = true;
                        }

                        boolean isBreak = true;
                        for (int i = 1; i <= n; i++) {
                            if (!flag[i]) {
                                isBreak = false;
                            }
                        }
                        if (isBreak) {
                            break;
                        }
                    } else {
                        remove.add(entry.getKey());
                        flag[entry.getKey()] = true;
                    }

                }
                for (int i = 1; i <= n; i++) {
                    if (!flag[i]) {
                        result++;
                    }
                }
                System.out.println(result);
            }
        }
    }
}
