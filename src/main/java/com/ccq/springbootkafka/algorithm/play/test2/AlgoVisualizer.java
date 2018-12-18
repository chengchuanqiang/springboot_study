package com.ccq.springbootkafka.algorithm.play.test2;

import com.ccq.springbootkafka.algorithm.play.template.AlgoVisHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private int[] data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n) {

        // 初始化数据
        // TODO: 初始化数据
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = 100;
        }

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(this::run).start();
        });
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, 100);

    }

    // 动画逻辑
    private void run() {

        // TODO: 编写自己的动画逻辑
        while (true) {
            Arrays.sort(data);
            frame.render(data);
            AlgoVisHelper.pause(20);
            for (int i = 0; i < data.length; i++) {
                int j = (int) (Math.random() * data.length);
                data[i] -= 2;
                data[j] += 2;
            }
        }

    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
