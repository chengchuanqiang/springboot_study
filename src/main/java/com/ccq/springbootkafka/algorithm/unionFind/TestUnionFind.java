package com.ccq.springbootkafka.algorithm.unionFind;

import com.ccq.springbootkafka.algorithm.unionFind.impl.*;

import java.util.Random;

/********************************
 *** 测试并查集
 ***@Author chengchuanqiang
 ***@Date 2018/7/25 18:07
 ***@Version 1.0.0
 ********************************/
public class TestUnionFind {

    private static int n; //数组大小
    private static int m; //查询两个数关联次数
    private static int[] connectX;
    private static int[] connectY;
    private static int[] judgeX;
    private static int[] judgeY;


    public void init(){
        System.out.println("数据准备开始...............");
        n = 1000000;
        m = 10000000;
        Random random = new Random();
        connectX = new int[n];
        connectY = new int[n];
        for (int i = 0; i < n; i++) {
            connectX[i] = random.nextInt(n);
            connectY[i] = random.nextInt(n);
        }
        judgeX = new int[m];
        judgeY = new int[m];
        for (int i = 0; i < m; i++) {
            judgeX[i] = random.nextInt(n);
            judgeY[i] = random.nextInt(n);
        }
        System.out.println("数据准备完成...............");
    }

    public void test(UnionFind unionFind, int n, int m) {

        long startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
            unionFind.connect(connectX[i], connectY[i]);
        }
        for (int i = 0; i < m; i++) {
            unionFind.isConnect(judgeX[i], judgeY[i]);
        }
        long endTime = System.nanoTime();
        String name = unionFind.getClass().getName();
        name = name.substring(name.lastIndexOf(".") + 1);
        System.out.println(name + " : " + (endTime - startTime) / 1000000000.0 + " s");

    }

    public static void main(String[] args) {

        TestUnionFind test = new TestUnionFind();

//        UnionFind quickFind = new QuickFind(n);
//        test.test(quickFind, n, m);
//
//        UnionFind quickUnion = new QuickUnion(n);
//        test.test(quickUnion, n, m);
        int i = 0;
        int sum = 5;
        while((i++) < sum){
            System.out.println("==============第 [" + i + "] 次==============");
            test.init();
            UnionFind weightQuickUnion = new WeightQuickUnion(n);
            test.test(weightQuickUnion, n, m);

            UnionFind heightQuickUnion = new HeightQuickUnion(n);
            test.test(heightQuickUnion, n, m);

            UnionFind weightQuickUnionPathCompression = new WeightQuickUnionPathCompression(n);
            test.test(weightQuickUnionPathCompression, n, m);

            UnionFind heightQuickUnionPathCompression = new HeightQuickUnionPathCompression(n);
            test.test(heightQuickUnionPathCompression, n, m);

            System.out.println();
        }
    }

}
