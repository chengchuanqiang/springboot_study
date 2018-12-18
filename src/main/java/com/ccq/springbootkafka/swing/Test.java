package com.ccq.springbootkafka.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: ChengChuanQiang
 * @Description: 测试
 * @Date: Created in 2018/9/24 14:54
 */
public class Test extends JFrame {

    public static void main(String args[]) {
        Test d = new Test();
        d.GUI();
    }

    public void GUI() {
        setLayout(new BorderLayout(5, 5));
        setTitle("图像测试");

        JPanel panel = new JPanel();
        JLabel imgLabel = new JLabel();
        System.out.println(this.getClass().getClassLoader().getResource("img/1.jpg").getPath());
        ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/1.jpg").getPath());// 创建图片对象
        imgLabel.setIcon(img);
        panel.add(imgLabel);

        JLabel txtLabel = new JLabel();
        txtLabel.setText("我是图片我是图片我是图片我是图片我是图片我是图片");

        getContentPane().add("North", txtLabel);//将按钮添加到窗口中
        getContentPane().add("South", panel);

        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 让JFrame的关闭按钮起作用
        setLocationRelativeTo(null); //让窗体居中显示
        setVisible(true);// 显示JFrame
    }


}
