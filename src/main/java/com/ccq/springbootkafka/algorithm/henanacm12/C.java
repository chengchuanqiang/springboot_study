package com.ccq.springbootkafka.algorithm.henanacm12;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/********************************
 *** http://nyoj.top/web/contest/problem/cid/112/num/C
 ***@Author chengchuanqiang
 ***@Date 2019/5/23 15:05
 ***@Version 1.0.0
 ********************************/
public class C {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            int k = input.nextInt();
            String[] a = new String[n];
            for (int i = 0; i < n; i++) {
                a[i] = input.next();
            }
            String t = input.next();
            List<String> tempList = new ArrayList<>();
            int index = 0;
            while (index < t.length()) {
                for (int i = 0; i < n; i++) {
                    int indexOf = t.indexOf(a[i], index);
                    if (indexOf == index) {
                        index += a[i].length();
                        tempList.add(a[i]);
                        break;
                    }
                }
            }

            while ((k--) > 0) {
                String s = input.next();
                int res = 0;
                int i = 0;
                for (String temp : tempList) {
                    String substring = s.substring(i, i + temp.length());
                    for (int j = 0; j < substring.length(); j++) {
                        if (temp.charAt(j) != substring.charAt(j)) {
                            res++;
                            break;
                        }
                    }
                    i += temp.length();
                }
                System.out.println(res);
            }
        }
    }
}
