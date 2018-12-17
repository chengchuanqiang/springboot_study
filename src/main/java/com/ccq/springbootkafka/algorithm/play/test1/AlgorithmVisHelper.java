package com.ccq.springbootkafka.algorithm.play.test1;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * @Author: ChengChuanQiang
 * @Description: 工具类
 * @Date: Created in 2018/12/15 16:36
 */
public class AlgorithmVisHelper {

    private AlgorithmVisHelper() {
    }

    /**
     * 设置线条的宽度
     *
     * @param g2d   图形对象
     * @param width 宽度
     */
    public static void setStrokeWidth(Graphics2D g2d, int width) {
        g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    /**
     * 绘制一个空心圆
     *
     * @param g2d 图形对象
     * @param x   圆心横坐标
     * @param y   圆心纵坐标
     * @param r   半径
     */
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    /**
     * 绘制一个实心圆
     *
     * @param g2d 图形对象
     * @param x   圆心横坐标
     * @param y   圆心纵坐标
     * @param r   半径
     */
    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }

    /**
     * 设置图形颜色
     *
     * @param g2d   图形对象
     * @param color 颜色
     */
    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    /**
     * 暂停时间
     * @param time 时间 毫秒
     */
    public static void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
