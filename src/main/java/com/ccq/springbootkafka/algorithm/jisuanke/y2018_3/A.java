package com.ccq.springbootkafka.algorithm.jisuanke.y2018_3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @Description:https://nanti.jisuanke.com/t/A1705
 * @Author: ChengChuanQiang
 * @Date: 2019/5/19 10:54
 */
public class A {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int T = input.nextInt();
        while ((T--) > 0) {
            int n = input.nextInt();
            List<House> houseList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                houseList.add(new House(input.nextInt(), input.nextInt()));
            }

            houseList.sort(Comparator.comparing(house -> house.s));

            double res = 0;
            for (int i = 1; i < houseList.size(); i++) {
                House house1 = houseList.get(i - 1);
                House house2 = houseList.get(i);
                double temp = getK(house1, house2);
                if (temp == -1) {
                    res = -1;
                    break;
                }
                res = Math.max(res, temp);
            }

            if (res == -1) {
                System.out.println("-1");
            } else {
                System.out.printf("%.6f\n", res);
            }
        }
    }

    private static double getK(House house1, House house2) {
        double s = house1.s - house2.s;
        double p = house1.p - house2.p;
        if (s == 0) {
            return -1;
        }
        return Math.abs(p / s);
    }

    static class House {
        int s;
        int p;

        public House(int s, int p) {
            this.s = s;
            this.p = p;
        }
    }
}
