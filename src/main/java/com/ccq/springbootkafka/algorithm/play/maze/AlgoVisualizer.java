package com.ccq.springbootkafka.algorithm.play.maze;

import com.ccq.springbootkafka.algorithm.play.template.AlgoVisHelper;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.*;

public class AlgoVisualizer {

    private static final int blockSize = 8;
    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // TODO: 创建自己的数据
    private MazeData data;      // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int N, int M) {

        // 初始化数据
        data = new MazeData(N, M);
        int sceneWidth = M * blockSize;
        int sceneHeight = N * blockSize;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Maze", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(this::randomRun2).start();
        });
    }

    public static void main(String[] args) {

        int N = 121;
        int M = 201;

        new AlgoVisualizer(N, M);
    }

    private void randomRun2() {
        setData(-1, -1);

        RandomQueue2<Point> queue = new RandomQueue2<>();
        queue.add(new Point(data.getStartX(), data.getStartY() + 1));
        data.visited[data.getStartX()][data.getStartY() + 1] = true;
        data.openMist(data.getStartX(),data.getStartY() + 1);

        while (!queue.isEmpty()) {
            Point curr = queue.remove();

            for (int[] d : dir) {
                int newX = curr.getX() + d[0] * 2;
                int newY = curr.getY() + d[1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    setData(curr.getX() + d[0], curr.getY() + d[1]);
                    data.visited[newX][newY] = true;
                    data.openMist(newX, newY);
                    queue.add(new Point(newX, newY));
                }
            }
        }
        setData(-1, -1);
    }

    private void randomRun() {
        setData(-1, -1);

        RandomQueue<Point> queue = new RandomQueue<>();
        queue.add(new Point(data.getStartX(), data.getStartY() + 1));
        data.visited[data.getStartX()][data.getStartY() + 1] = true;

        while (!queue.isEmpty()) {
            Point curr = queue.remove();

            for (int[] d : dir) {
                int newX = curr.getX() + d[0] * 2;
                int newY = curr.getY() + d[1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    setData(curr.getX() + d[0], curr.getY() + d[1]);
                    data.visited[newX][newY] = true;
                    queue.add(new Point(newX, newY));
                }
            }
        }
        setData(-1, -1);
    }

    private void queueRun() {
        setData(-1, -1);

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(data.getStartX(), data.getStartY() + 1));
        data.visited[data.getStartX()][data.getStartY() + 1] = true;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();

            for (int[] d : dir) {
                int newX = curr.getX() + d[0] * 2;
                int newY = curr.getY() + d[1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    setData(curr.getX() + d[0], curr.getY() + d[1]);
                    data.visited[newX][newY] = true;
                    queue.add(new Point(newX, newY));
                }
            }
        }
        setData(-1, -1);
    }

    // 动画逻辑
    private void stackRun() {
        setData(-1, -1);

        Stack<Point> stack = new Stack<>();
        stack.push(new Point(data.getStartX(), data.getStartY() + 1));
        data.visited[data.getStartX()][data.getStartY() + 1] = true;

        while (!stack.isEmpty()) {
            System.out.println(stack.size());
            Point curr = stack.pop();

            for (int[] d : dir) {
                int newX = curr.getX() + d[0] * 2;
                int newY = curr.getY() + d[1] * 2;

                if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                    setData(curr.getX() + d[0], curr.getY() + d[1]);
                    data.visited[newX][newY] = true;
                    stack.push(new Point(newX, newY));
                }
            }
        }
        setData(-1, -1);
    }

    /**
     * 递归
     * go(data.getStartX(), data.getStartY() + 1);
     */
    private void go(int x, int y) {
        data.visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + dir[i][0] * 2;
            int newY = y + dir[i][1] * 2;
            if (data.inArea(newX, newY) && !data.visited[newX][newY]) {
                setData(x + dir[i][0], y + dir[i][1]);
                go(newX, newY);
            }
        }
    }

    private void setData(int x, int y) {
        if (data.inArea(x, y)) {
            data.maze[x][y] = MazeData.ROAD;
        }
        frame.render(data);
        AlgoVisHelper.pause(5);
    }


    private boolean goPath(int x, int y) {

        data.visited[x][y] = true;
        setPathData(x, y, true);

        if (x == data.getEndX() && y == data.getEndY()) {
            return true;
        }
        for (int[] d : dir) {
            int newX = x + d[0];
            int newY = y + d[1];
            if (data.inArea(newX, newY) && !data.visited[newX][newY] && data.maze[newX][newY] == MazeData.ROAD) {
                if (goPath(newX, newY)) {
                    return true;
                }
            }
        }

        // 回溯
        setPathData(x, y, false);
        return false;
    }

    private void setPathData(int x, int y, boolean isPath) {
        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgoVisHelper.pause(5);
    }


    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                for (int i = 0; i < data.getN(); i++) {
                    for (int j = 0; j < data.getM(); j++) {
                        data.visited[i][j] = false;
                    }
                }
                new Thread(() -> goPath(data.getStartX(), data.getStartY())).start();
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
