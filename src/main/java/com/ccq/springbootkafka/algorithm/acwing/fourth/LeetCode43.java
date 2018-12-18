package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.math.BigInteger;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/13 9:25
 ***@Version 1.0.0
 ********************************/
public class LeetCode43 {

    // Java偷懒版本（感觉不好）
    public String multiply1(String num1, String num2) {
        BigInteger n1 = new BigInteger(num1, 10);
        BigInteger n2 = new BigInteger(num2, 10);

        BigInteger res = n1.multiply(n2);
        return String.valueOf(res);
    }

    public String multiply(String num1, String num2) {
        int[] n1 = new int[num1.length()];
        int[] n2 = new int[num2.length()];

        int[] res = new int[num1.length() + num2.length()];

        for (int i = 0; i < num1.length(); i++) {
            n1[num1.length() - i - 1] = num1.charAt(i) - '0';
        }

        for (int i = 0; i < num2.length(); i++) {
            n2[num2.length() - i - 1] = num2.charAt(i) - '0';
        }

        for (int i = 0; i < n1.length; i++) {
            for (int j = 0; j < n2.length; j++) {
                res[i + j] += n1[i] * n2[j];
                res[i + j + 1] += res[i + j] / 10;
                res[i + j] %= 10;
            }
        }
//        System.out.println(Arrays.toString(res));

        int len = res.length;
        while (len > 1 && res[len - 1] == 0) {
            len--;
        }
        StringBuilder sbf = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            sbf.append(res[i]);
        }
        return sbf.toString();

    }

    public static void main(String[] args) {
        LeetCode43 test = new LeetCode43();
        System.out.println(test.multiply("2", "3"));
        System.out.println(test.multiply("123", "456"));
        System.out.println(test.multiply("401716832807512840963", "167141802233061013023557397451289113296441069"));


    }
}
