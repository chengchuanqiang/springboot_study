package com.ccq.springbootkafka.outer;

/********************************
 *** outer的使用
 *** <break outer;> 这行代码将会导致结束outer标签指定的外层循环
 *** 不是结束break所在的循环，outer可以用其它的词来代替
 ***@Author chengchuanqiang
 ***@Date 2018/11/19 13:54
 ***@Version 1.0.0
 ********************************/
public class OuterTest {


    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        outer:
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (i == 1) {
                    break outer;
                }
                System.out.println("i == " + i + ", j == " + j);
            }
        }

    }
}

