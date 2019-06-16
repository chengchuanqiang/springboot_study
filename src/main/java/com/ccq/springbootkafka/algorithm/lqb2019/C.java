package com.ccq.springbootkafka.algorithm.lqb2019;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/3/24 16:02
 */
public class C {

    public static void main(String[] args) {

        int[] f = new int[20190326];
        f[0] = 1;
        f[1] = 1;
        f[2] = 1;
        f[3] = 1;

        for (int i = 4; i <= 20190325; i++) {
            f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % 1000000;
        }

        System.out.println(f[20190324]);

    }

}
