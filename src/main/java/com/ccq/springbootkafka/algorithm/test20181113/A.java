package com.ccq.springbootkafka.algorithm.test20181113;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/11/13 22:08
 */
public class A {
    private static String AD = "AD";
    private static String BC = "BC";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        while ((n--) > 0) {
            String s;
            List<Integer> times = new ArrayList<>();
            while (!(s = input.next()).equals("/")) {
                int year = Integer.valueOf(s.substring(0, 4));
                String str = s.substring(4);
                if (BC.equals(str)) {
                    year = -year + 1;
                }
                times.add(year);
            }

            times.sort(Comparator.naturalOrder());
//            System.out.println(times);

            int min = times.get(1) - times.get(0);
            int flag = 1;
            for (int i = 2; i < times.size(); i++) {
                if (times.get(i) - times.get(i - 1) < min) {
                    min = times.get(i) - times.get(i - 1);
                    flag = i;
                }
            }

            StringBuffer sbf = new StringBuffer();

            if (times.get(flag - 1) <= 0) {
                sbf.append(Math.abs(times.get(flag - 1) - 1)).append(BC).append(" ");
            } else {
                sbf.append(Math.abs(times.get(flag - 1))).append(AD).append(" ");
            }

            if (times.get(flag) <= 0) {
                sbf.append(Math.abs(times.get(flag) - 1)).append(BC);
            } else {
                sbf.append(Math.abs(times.get(flag))).append(AD);
            }

            System.out.println(sbf.toString());
        }
    }
}