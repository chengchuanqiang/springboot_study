package com.ccq.springbootkafka.algorithm.test20181210;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/12/10 17:58
 ***@Version 1.0.0
 ********************************/
public class D {
    public static void main(String[] args) {
        int n = 1000001;
        int a[] = new int[n];

        for (int i = 1; i < n; i++) {
            int j = i;
            while (true) {
                int temp = d(j);
                if (temp >= n || a[temp] == 1) {
                    break;
                }
                a[temp] = 1;
                j = temp;
            }
        }

        for (int i = 1; i < n; i++) {
            if (a[i] != 1) {
                System.out.println(i);
            }
        }
    }

    public static int d(int n) {
        char[] s = String.valueOf(n).toCharArray();
        int res = n;
        for (char c : s) {
            res += (c - '0');
        }
        return res;
    }
}
