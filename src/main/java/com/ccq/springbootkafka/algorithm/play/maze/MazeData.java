package com.ccq.springbootkafka.algorithm.play.maze;

/**
 * @Author: ChengChuanQiang
 * @Description: 迷宫类
 * @Date: Created in 2018/12/23 20:46
 */
public class MazeData {

    public static final char ROAD = ' ';
    public static final char WALL = '#';
    public char[][] maze;
    public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] inMist;
    private int N;
    private int M;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public MazeData(int N, int M) {
        if (N % 2 == 0 || M % 2 == 0) {
            throw new IllegalArgumentException("迷宫的高和宽必须是奇数");
        }

        this.N = N;
        this.M = M;

        maze = new char[N][M];
        visited = new boolean[N][M];
        path = new boolean[N][M];
        inMist = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    maze[i][j] = ROAD;
                } else {
                    maze[i][j] = WALL;
                }
                visited[i][j] = false;
                path[i][j] = false;

                inMist[i][j] = true;
            }
        }

        this.startX = 1;
        this.startY = 0;
        this.endX = N - 2;
        this.endY = M - 1;

        maze[startX][startY] = ROAD;
        maze[endX][endY] = ROAD;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void openMist(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("x or y is out of index in openMist function!");
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inArea(i, j)) {
                    inMist[i][j] = false;
                }
            }
        }

        return;
    }

}
