package com.ccq.springbootkafka.algorithm.yjx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/3/16 10:45
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        while (n-- != 0) {
            String str = input.next();
            StringBuffer sb;
            while (true) {
                sb = new StringBuffer();
                for (int i = 0; i < str.length(); ) {
                    if (str.length() - i >= 4 && str.charAt(i) == str.charAt(i + 1) && str.charAt(i + 2) == str.charAt(i + 3)) {
                        // aabb
                        sb.append(str.substring(i, i + 3));
                        i += 4;
                    } else if (str.length() - i >= 3 && str.charAt(i) == str.charAt(i + 1) && str.charAt(i) == str.charAt(i + 2)) {
                        // aaa
                        sb.append(str.substring(i, i + 2));
                        i += 3;
                    } else {
                        sb.append(str.charAt(i));
                        i++;
                    }
                }
                if (str.equals(sb.toString())) {
                    break;
                }
                str = sb.toString();
            }
            System.out.println(sb.toString());
        }

    }

}
