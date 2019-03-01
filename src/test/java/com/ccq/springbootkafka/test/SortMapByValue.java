package com.ccq.springbootkafka.test;

import com.ccq.springbootkafka.domain.User;

import java.util.*;

/********************************
 *** map 按照value进行排序
 ***@Author chengchuanqiang
 ***@Date 2019/2/28 19:09
 ***@Version 1.0.0
 ********************************/
public class SortMapByValue {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setAge(1);
        user1.setUserName("test1");

        User user2 = new User();
        user2.setAge(2);
        user2.setUserName("test2");

        User user3 = new User();
        user3.setAge(3);
        user3.setUserName("test3");

        User user4 = new User();
        user4.setAge(4);
        user4.setUserName("test4");

        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(1, user4);
        userMap.put(2, user1);
        userMap.put(3, user2);
        userMap.put(4, user3);

        System.out.println("==========排序前=========");
        userMap.forEach((key, user) -> System.out.println(key + ", age=" + user.getAge() + ", name" + user.getUserName()));

        Map<Integer, User> resultMap = new LinkedHashMap<>();
        userMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(User::getAge))).forEach(e -> resultMap.put(e.getKey(), e.getValue()));
        System.out.println("==========排序后=========");
        resultMap.forEach((key, user) -> System.out.println(key + ", age=" + user.getAge() + ", name" + user.getUserName()));

    }


}
