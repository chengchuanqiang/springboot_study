package com.ccq.springbootkafka.algorithm.job20190616;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/6/25 22:59
 */
public class StackLevel {

    private int level = 1;

    public static void main(String[] args) throws Throwable {

        StackLevel sl = new StackLevel();

        try {

            sl.stackLevel();

        } catch (StackOverflowError e) {

            System.out.println(sl.level);

        }

    }

    public void stackLevel() {

        level++;
//        StringBuffer stringBuffer = new StringBuffer();

        stackLevel();
    }
}
