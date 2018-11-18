package com.ccq.springbootkafka.algorithm.test20181113;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/11/13 23:11
 */
public class C {
    private static int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] map = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = input.nextInt();
            }
        }

//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        Point s = new Point(4, 0);
        Point e = new Point(0, 4);
        System.out.println(bfs(map, s, e) ? "yes" : "no");

    }

    public static boolean bfs(int[][] map, Point s, Point e) {
        boolean[][] flag = new boolean[5][5];
        Queue<Point> q = new ArrayDeque<>();
        q.add(s);
        flag[s.x][s.y] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.x == e.x && curr.y == e.y) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int x = curr.x + dir[i][0];
                int y = curr.y + dir[i][1];
                if (x < 0 || x >= 5 || y < 0 || y >= 5) {
                    continue;
                }
                if (flag[x][y] || map[x][y] == 0) {
                    continue;
                }
                q.add(new Point(x, y));
                flag[x][y] = true;
//                System.out.println(x + " " + y);
            }

        }
        return false;
    }

}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
