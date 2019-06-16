package com.ccq.springbootkafka.algorithm.lqb2019;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/3/24 15:52
 */
public class B {

    public static void main(String[] args) {
        String str = "0100110001010001";
//        String str = "aaab"
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            for (int len = 1; len < str.length() - i + 1; len++) {

                String next = str.substring(i, i + len);
                if (map.containsKey(next)) {
                    map.put(next, map.get(next) + 1);
                } else {
                    map.put(next, 1);
                }
            }
        }

        System.out.println(map.keySet().size());
    }

}
