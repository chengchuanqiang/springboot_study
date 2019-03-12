package com.ccq.springbootkafka.algorithm.play.moveTheBox;

import com.ccq.springbootkafka.algorithm.play.template.AlgoVisHelper;
import com.ccq.springbootkafka.designPattern.templatePattern.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    private GameData data;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title) {

        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void render(GameData data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        private ArrayList<Color> colorList;
        private HashMap<Character, Color> colorMap;

        public AlgoCanvas() {
            // 双缓存
            super(true);
            colorList = new ArrayList<>();
            colorList.add(AlgoVisHelper.Red);
            colorList.add(AlgoVisHelper.Purple);
            colorList.add(AlgoVisHelper.Blue);
            colorList.add(AlgoVisHelper.Teal);
            colorList.add(AlgoVisHelper.LightGreen);
            colorList.add(AlgoVisHelper.Lime);
            colorList.add(AlgoVisHelper.Amber);
            colorList.add(AlgoVisHelper.DeepOrange);
            colorList.add(AlgoVisHelper.Brown);
            colorList.add(AlgoVisHelper.BlueGrey);

            colorMap = new HashMap<>();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth / data.getM();
            int h = canvasHeight / data.getN();
            Board showBoard = data.getShowBoard();

            for (int i = 0; i < data.getN(); i++) {
                for (int j = 0; j < data.getM(); j++) {
                    char c = showBoard.getData(i, j);
                    if (c != Board.EMPTY) {
                        if (!colorMap.containsKey(c)) {
                            colorMap.put(c, colorList.get(colorMap.size()));
                        }

                        Color color = colorMap.get(c);
                        AlgoVisHelper.setColor(g2d, color);
                        AlgoVisHelper.fillRectangle(g2d, j * h + 2, i * w + 2, w - 4, h - 4);
                    }else {
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.White);
                        AlgoVisHelper.fillRectangle(g2d, j * h + 2, i * w + 2, w - 4, h - 4);
                    }
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Black);
                    String text = String.format("( %d , %d )", i, j);
                    AlgoVisHelper.drawText(g2d, text, j * h + h / 2, i * w + w / 2);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}


