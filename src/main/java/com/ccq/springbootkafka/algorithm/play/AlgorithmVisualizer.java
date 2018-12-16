package com.ccq.springbootkafka.algorithm.play;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/12/15 17:35
 */
public class AlgorithmVisualizer {

    private Circle[] circles;
    private AlgorithmFrame algorithmFrame;
    private boolean isAnimated = true;

    public AlgorithmVisualizer(int canvasWidth, int canvasHeight, int n) {
        circles = new Circle[n];
        int r = 50;
        // Math.random() == [0 - 1)
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * (canvasWidth - 2 * r)) + r;
            int y = (int) (Math.random() * (canvasHeight - 2 * r)) + r;
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, r, vx, vy);
        }

        EventQueue.invokeLater(() -> {
            algorithmFrame = new AlgorithmFrame("Welcome", canvasWidth, canvasHeight);
            algorithmFrame.addKeyListener(new AlgoKeyListener());
            algorithmFrame.addMouseListener(new AlgoMouseListener());
            new Thread(this::run).start();
        });
    }

    public static void main(String[] args) {

        int canvasWidth = 800;
        int canvasHeight = 800;
        int n = 10;

        new AlgorithmVisualizer(canvasWidth, canvasHeight, n);
    }

    // 动画逻辑
    private void run() {
        while (true) {
            // 绘制数据
            algorithmFrame.render(circles);
            AlgorithmVisHelper.pause(20);

            // 更新数据
            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(0, 0, algorithmFrame.getCanvasWidth(), algorithmFrame.getCanvasHeight());
                }
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {

            e.translatePoint(0, -(algorithmFrame.getBounds().height - algorithmFrame.getCanvasHeight()));

//            Point point = e.getPoint();
//            System.out.println(point);

            for (Circle circle : circles) {
                if (circle.contain(e.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }

        }
    }

}
