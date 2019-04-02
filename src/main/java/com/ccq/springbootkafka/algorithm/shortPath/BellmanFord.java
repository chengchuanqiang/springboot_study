package com.ccq.springbootkafka.algorithm.shortPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/********************************
 *** bellman-ford 最短算法
 ***@Author chengchuanqiang
 ***@Date 2019/4/2 9:57
 ***@Version 1.0.0
 ********************************/
public class BellmanFord {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Edge> edges = new ArrayList<>();

        int n = input.nextInt();
        int m = input.nextInt();

        while ((m--) > 0) {
            int s = input.nextInt();
            int e = input.nextInt();
            int v = input.nextInt();
            edges.add(new Edge(s, e, v));
            edges.add(new Edge(e, s, v));
        }

        System.out.println(bellmanFord(edges, n));
    }

    private static int bellmanFord(List<Edge> edges, int n) {
        int[] d = new int[n];
        Arrays.fill(d, 9999);
        d[0] = 0;

        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (d[edge.e] > d[edge.s] + edge.v) {
                    d[edge.e] = d[edge.s] + edge.v;
                }
            }
        }

        return d[n - 1];
    }
}

class Edge {
    public int s;
    public int e;
    public int v;

    public Edge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }
}
