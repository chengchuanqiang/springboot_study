package com.ccq.springbootkafka.test;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/4/22 0:17
 */
public class Test {

    public static void main(String[] args) {

//        testInteger();
        testString();

    }

    private static void testInteger() {
        // Java Integer 类型存在一个IntegerCache，缓存池，范围是-128 ~ 127
        // 自动装箱 Integer.valueOf() 时，会到缓存池中获取，如果没有再进行new操作

        /**
         * 基本概念区分：
         * 1、Integer是int的包装类，int则是java的基础数据类型
         * 2、Integer变量必须实例化后才可以使用，而int变量不需要
         * 3、Integer实际是对象的引用，当new一个Integer是，实际上是生成一个指针指向此对象，而int则是直接存储数据
         * 4、Integer的默认值是null，int的默认值是0
         *
         * Integer，new Integer()，int比较
         * 1、两个new Integer()变量比较，永远是false
         * 2、Integer变量和new Integer()变量比较，永远是false
         * 3、两个Integer变量比较，如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量不在此区间，比较结果是false
         * 4、int变量与Integer，new Integer() 比较时，java会自动拆包为int，然后进行比较，实际上就变为两个int变量的比较，只要值相等，永远是true
         */


        Integer a = 59;
        int b = 59;
        Integer c = Integer.valueOf(59);
        Integer d = new Integer(59);

        System.out.println(a == b);    // true  进行自动装箱操作
        System.out.println(a == c);    // true
        System.out.println(c == d);    // false 使用 new 操作，实例化了新的对象
        System.out.println(b == d);    // true

        Integer e = 128;
        Integer f = 128;
        System.out.println(e == f);    // false 数字128 不在缓存池中，所以都进行了 new 操作

        Integer g = 127;
        Integer h = 127;
        System.out.println(g == h);    // true 数字127 在缓存池中，所以取的是一个对象

        Integer o = 129;
        Integer p = new Integer(129);
        System.out.println(o == p);    // false

        Integer r = 127;
        Integer s = new Integer(127);
        System.out.println(r == s);    // false


        int t = 1111;
        Integer u = new Integer(1111);
        System.out.println(t == u);    // true
    }


    private static void testString() {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");

        System.out.println(s1 == s2);     // true
        System.out.println(s1 == s3);     // false

        testInteger();
    }
}
