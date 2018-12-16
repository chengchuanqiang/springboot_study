package com.ccq.springbootkafka.algorithm.play;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/12/15 15:53
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    private Circle[] circles;

    public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        // 创建画布
        AlgorithmCanvas canvas = new AlgorithmCanvas();
        this.setContentPane(canvas);
        this.pack();

        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public AlgorithmFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void render(Circle[] circles) {
        this.circles = circles;
        this.repaint();
    }

    private class AlgorithmCanvas extends JPanel {

        public AlgorithmCanvas() {
            // 支持双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿,使图像更平滑
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 具体绘制
            AlgorithmVisHelper.setStrokeWidth(g2d, 5);
            AlgorithmVisHelper.setColor(g2d, Color.RED);

            for (Circle circle : circles) {
                if (circle.isFilled) {
                    AlgorithmVisHelper.fillCircle(g2d, circle.x, circle.y, circle.getR());
                } else {
                    AlgorithmVisHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }

    }
}
