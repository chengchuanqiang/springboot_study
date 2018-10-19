package com.ccq.springbootkafka.algorithm.dfs;

import java.util.*;

/********************************
 *** 使用回溯法解决01背包问题
 ***@Author chengchuanqiang
 ***@Date 2018/10/17 9:48
 ***@Version 1.0.0
 ********************************/
public class Package01 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        int T = input.nextInt();
        while (input.hasNext()) {
            Package p = new Package();
            p.num = input.nextInt();
            p.ww = input.nextInt();
            Goods[] goods = new Goods[p.num + 1];
            for (int i = 1; i <= p.num; i++) {
                goods[i] = new Goods(input.nextInt(), input.nextInt());
            }
            p.goods = goods;
            List<List<Integer>> res = new ArrayList<>();
            Integer[] curr = new Integer[p.num + 1];
            Arrays.fill(curr, 0);
            dfs2(1, p, curr, res, 0, 0);
            System.out.println(p.bestVal);
        }
        input.close();
    }

    private static void test() {
        Package p = new Package();
        p.num = 4;
        p.ww = 10;
        Goods goods1 = new Goods(2, 6);
        Goods goods2 = new Goods(5, 3);
        Goods goods3 = new Goods(4, 5);
        Goods goods4 = new Goods(2, 4);
        Goods[] goods = new Goods[5];
        goods[1] = goods1;
        goods[2] = goods2;
        goods[3] = goods3;
        goods[4] = goods4;
        p.goods = goods;
        List<List<Integer>> res = new ArrayList<>();
        Integer[] curr = new Integer[5];
        Arrays.fill(curr, 0);
        dfs(1, p, curr, res, 0, 0);

        System.out.println("bastVal = " + p.bestVal);
        res.forEach(System.out::print);
    }

    private static void dfs(int i, Package p, Integer[] curr, List<List<Integer>> res, int currVal, int currW) {

        // 每次递归意味着currVal都有变化，所以需要进行判断
        if (currVal > p.bestVal) {
            res.clear();
            res.add(new ArrayList<>(Arrays.asList(curr)));
            p.bestVal = currVal;
        } else if (currVal == p.bestVal) {
            res.add(new ArrayList<>(Arrays.asList(curr)));
        }

        // 遍历到底得情况
        if (i > p.num) {
            return;
        }

        for (int j = i; j <= p.num; j++) {
            if (currW + p.goods[j].w <= p.ww) {
                curr[j] = 1;
                currW += p.goods[j].w;
                currVal += p.goods[j].v;

                dfs(j + 1, p, curr, res, currVal, currW);
                currW -= p.goods[j].w;
                currVal -= p.goods[j].v;
                curr[j] = 0;
            }
        }

    }

    private static void dfs2(int i, Package p, Integer[] curr, List<List<Integer>> res, int currVal, int currW) {
        // 遍历到底得情况
        if (i > p.num) {
            if (currVal > p.bestVal) {
                res.clear();
                res.add(new ArrayList<>(Arrays.asList(curr)));
                p.bestVal = currVal;
            } else if (currVal == p.bestVal) {
                res.add(new ArrayList<>(Arrays.asList(curr)));
            }
            return;
        }

        if (currW + p.goods[i].w <= p.ww) {
            curr[i] = 1;
            currW += p.goods[i].w;
            currVal += p.goods[i].v;
            dfs2(i + 1, p, curr, res, currVal, currW);
            currW -= p.goods[i].w;
            currVal -= p.goods[i].v;
        }

        if (bound(i + 1, p, currVal) > p.bestVal) {
            curr[i] = 0;
            dfs2(i + 1, p, curr, res, currVal, currW);
        }
    }

    private static int bound(int i, Package p, int currVal) {
        while (i <= p.num) {
            currVal += p.goods[i++].v;
        }
        return currVal;
    }

    static class Package {
        int num; // 物品总数
        int ww;  // 背包最大容量
        int bestVal; // 背包最优价值
        Goods[] goods; //物品
    }

    static class Goods {
        int w;// 物品重量
        int v;// 物品价值

        Goods(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

}
