package com.ccq.springbootkafka.algorithm.play.test1;

import java.awt.*;

/**
 * @Author: ChengChuanQiang
 * @Description: 圆实体类
 * @Date: Created in 2018/12/15 17:02
 */
public class Circle {

    public int x;
    public int y;
    public int vx;
    public int vy;
    public boolean isFilled = false;
    private int r;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getR() {
        return r;
    }

    public void move(int minx, int miny, int maxx, int maxy) {
        x += vx;
        y += vy;

        checkCollision(minx, miny, maxx, maxy);
    }

    private void checkCollision(int minx, int miny, int maxx, int maxy) {

        if (x - r < minx) {
            x = r;
            vx = -vx;
        }

        if (x + r > maxx) {
            x = maxx - r;
            vx = -vx;
        }

        if (y - r < miny) {
            y = r;
            vy = -vy;
        }

        if (y + r > maxx) {
            y = maxy - r;
            vy = -vy;
        }


    }

    public boolean contain(Point point) {

        return (x - point.x) * (x - point.x) + (y - point.y) * (y - point.y) <= r * r;

    }
}
