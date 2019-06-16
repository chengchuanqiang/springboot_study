package com.ccq.springbootkafka.algorithm.zuji;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main1 {

    private static final int dir[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<List<String>> map = new ArrayList<>();
        while (input.hasNextLine()) {
            String str = input.nextLine();
            if (str.equals("")) {
                break;
            }
            String[] s = str.split(" ");
            map.add(new ArrayList<>(Arrays.asList(s)));
        }
        String[][] mapx = new String[map.size()][map.get(0).size()];
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                mapx[i][j] = map.get(i).get(j);
            }
        }
        System.out.println(solve(mapx));
    }

    private static int solve(String[][] mapx) {
        int res = 0;
        boolean isChange;
        while (true) {
            isChange = false;
            for (int i = 0; i < mapx.length; i++) {
                for (int j = 0; j < mapx[i].length; j++) {
                    if (mapx[i][j].equals("2")) {
                        for (int k = 0; k < dir.length; k++) {
                            int newX = i + dir[k][0];
                            int newY = j + dir[k][1];
                            if (newX >= 0 && newX < mapx.length && newY >= 0 && newY < mapx[i].length && mapx[newX][newY].equals("1")) {
                                isChange = true;
                                mapx[newX][newY] = "-1";
                            }
                        }
                    }
                }
            }
            if (isChange) {
                for (int i = 0; i < mapx.length; i++) {
                    for (int j = 0; j < mapx[i].length; j++) {
                        if (mapx[i][j].equals("-1")) {
                            mapx[i][j] = "2";
                        }
                    }
                }
                res++;
            }
            if (!isChange) {
                break;
            }
        }

        for (int i = 0; i < mapx.length; i++) {
            for (int j = 0; j < mapx[i].length; j++) {
                if (mapx[i][j].equals("1")) {
                    return -1;
                }
            }
        }
        return res;
    }
}