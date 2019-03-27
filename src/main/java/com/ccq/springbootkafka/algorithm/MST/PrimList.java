package com.ccq.springbootkafka.algorithm.MST;

import java.util.*;

/********************************
 *** 优先队列实现prim算法
 ***@Author chengchuanqiang
 ***@Date 2019/3/27 14:34
 ***@Version 1.0.0
 ********************************/
public class PrimList {

    private static int prim(List<List<Edge>> edges) {
        int n = edges.size();
        int result = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        boolean[] vis = new boolean[n];
        Arrays.fill(vis, false);

        Edge s = new Edge(0, 0);
        queue.add(s);
        int count = 0;
        while (!queue.isEmpty()) {

            Edge edge = queue.poll();
            while (edge != null && vis[edge.to]) {
                edge = queue.poll();
            }

            // 不连通
            if (null == edge) {
                return -1;
            }

            vis[edge.to] = true;
            result += edge.weight;
            count++;

            // 添加边成功
            if (count == n) {
                break;
            }

            for (int i = 0; i < edges.get(edge.to).size(); i++) {
                if (!vis[edges.get(edge.to).get(i).to]) {
                    queue.add(edges.get(edge.to).get(i));
                }
            }
        }


        return result;
    }

    public static void main(String[] args) {

        List<List<Edge>> edges = new ArrayList<>();
        List<Edge> edge0 = new ArrayList<>();
        edge0.add(new Edge(1, 6));
        edge0.add(new Edge(2, 1));
        edge0.add(new Edge(3, 5));
        edges.add(edge0);

        List<Edge> edge1 = new ArrayList<>();
        edge1.add(new Edge(0, 6));
        edge1.add(new Edge(2, 5));
        edge1.add(new Edge(4, 3));
        edges.add(edge1);

        List<Edge> edge2 = new ArrayList<>();
        edge2.add(new Edge(0, 1));
        edge2.add(new Edge(1, 5));
        edge2.add(new Edge(3, 5));
        edge2.add(new Edge(5, 4));
        edges.add(edge2);

        List<Edge> edge3 = new ArrayList<>();
        edge3.add(new Edge(0, 5));
        edge3.add(new Edge(2, 5));
        edge3.add(new Edge(5, 2));
        edges.add(edge3);

        List<Edge> edge4 = new ArrayList<>();
        edge4.add(new Edge(1, 3));
        edge4.add(new Edge(2, 6));
        edge4.add(new Edge(5, 6));
        edges.add(edge4);

        List<Edge> edge5 = new ArrayList<>();
        edge5.add(new Edge(2, 4));
        edge5.add(new Edge(3, 2));
        edge5.add(new Edge(4, 6));
        edges.add(edge5);

        System.out.println(prim(edges));

    }

}

class Edge {
    int to;
    int weight;

    public Edge() {
    }

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

}