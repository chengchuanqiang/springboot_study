package com.ccq.springbootkafka.algorithm.jishao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2019/4/8 19:00
 ***@Version 1.0.0
 ********************************/
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Map<String, Location> map = new HashMap<>();
        Map<String, String> locationMap = new HashMap<>();
        while (true) {
            String arm = input.nextLine();
            if (arm.equals("")) {
                break;
            }
            String[] arms = arm.split(" ");
            Location location;
            switch (arms[2]) {
                case "Hold":
                    location = new Location(arms[1], null, 1);
                    map.put(arms[0], location);
                    locationMap.put(arms[1], arms[0]);
                    break;
                case "Move":
                    location = new Location(arms[1], arms[3], 1);
                    map.put(arms[0], location);
                    if (locationMap.containsKey(arms[3])) {
                        String x = locationMap.get(arms[3]);
                        Location location1 = map.get(x);
                        location1.w--;
                        map.put(x, location1);
                    }
                    break;
                case "Support":
                    location = new Location(arms[1], null, 1);
                    map.put(arms[0], location);
                    if (locationMap.containsKey(arms[3])) {
                        String x = locationMap.get(arms[3]);
                        Location location1 = map.get(x);
                        location1.w++;
                        map.put(x, location1);
                    }
                    break;
                default:
                    break;
            }
        }

        map.forEach((key, location) -> {
            if (location.w <= 0) {
                System.out.println(key + " [dead]");
            } else {
                if (location.newAddress != null) {
                    System.out.println(key + " " + location.newAddress);
                } else {
                    System.out.println(key + " " + location.oldAddress);
                }
            }
        });

        input.close();
    }

}

class Location {
    public String oldAddress;
    public String newAddress;
    public int w;

    public Location(String oldAddress, String newAddress, int w) {
        this.oldAddress = oldAddress;
        this.newAddress = newAddress;
        this.w = w;
    }
}
