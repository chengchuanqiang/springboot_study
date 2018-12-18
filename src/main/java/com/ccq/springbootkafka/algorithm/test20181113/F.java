package com.ccq.springbootkafka.algorithm.test20181113;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/11/13 23:46
 */
public class F {


    private static Map<Integer, Character> init() {
        int[] a = new int[30];
        a[1] = 2;
        a[2] = 3;
        Map<Integer, Character> map = new HashMap<>();
        map.put(a[0], ' ');
        map.put(a[1], 'A');
        map.put(a[2], 'B');
        for (int i = 3; i <= 26; i++) {
            a[i] = a[i - 1] + a[i - 2];
            map.put(a[i], (char) ('A' + i - 1));
        }
        return map;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<Integer, Character> map = init();
        int N = input.nextInt();
        StringBuffer sbf = new StringBuffer();
        while ((N--) > 0) {
            sbf.append(map.get(input.nextInt()));
        }
        System.out.println(sbf.toString());
    }

}
