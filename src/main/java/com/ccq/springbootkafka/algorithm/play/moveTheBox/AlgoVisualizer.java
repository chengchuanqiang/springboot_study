package com.ccq.springbootkafka.algorithm.play.moveTheBox;

import com.ccq.springbootkafka.algorithm.play.template.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    private static final int blockSide = 80;
    private static final int DELAY = 5;
    private GameData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(String fileName) {

        // 初始化数据
        data = new GameData(fileName);
        int sceneWidth = data.getM() * blockSide;
        int sceneHeight = data.getN() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Move the Box Solver", sceneWidth, sceneHeight);
            new Thread(this::run).start();
        });
    }

    public static void main(String[] args) {

        String fileName = "/moveTheBox/2-24.txt";
        AlgoVisualizer visualizer = new AlgoVisualizer(fileName);

    }

    // 动画逻辑
    private void run() {
        setData();

        if (data.solve()) {
            System.out.println("The game has a solution!");
        } else {
            System.out.println("The game does NOT have a solution.");
        }

        setData();
    }

    private void setData() {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
}
