package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/27 18:07
 ***@Version 1.0.0
 ********************************/
public class LeetCode326 {

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        double res = Math.log10(n) / Math.log10(3);
        return res == Math.ceil(res);
    }

    public static void main(String[] args) {
        LeetCode326 test = new LeetCode326();

        System.out.println(test.isPowerOfThree(27));
        System.out.println(test.isPowerOfThree(0));
        System.out.println(test.isPowerOfThree(1));
    }
}
