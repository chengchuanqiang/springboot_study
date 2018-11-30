package com.ccq.springbootkafka.fastjson;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/********************************
 *** FastJson 循环引用测试
 ***@Author chengchuanqiang
 ***@Date 2018/11/30 16:42
 ***@Version 1.0.0
 ********************************/
public class TestJson {

    public static void test1() {
        // 重复引用
        List<User> userList = new ArrayList<>();
        User user = new User(1, "tom");
        userList.add(user);
        userList.add(user);

        System.out.println(JSON.toJSONString(userList));
    }

    public static void test2() {
        // 循环引用的特殊情况，自引用
        Map<String, Object> map = new HashMap<>();
        map.put("map", map);
        System.out.println(JSON.toJSONString(map));

        // map1引用了map2，而map2又引用map1，导致循环引用
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map1.put("map", map2);
        map2.put("map", map1);
        System.out.println(JSON.toJSONString(map1));

    }

    public static void test3() {
        // 解决重复引用
        List<User> userList = new ArrayList<>();
        User user = new User(1, "tom");
        userList.add(user);

        User user2 = new User();
        BeanUtils.copyProperties(user, user2);
        userList.add(user2);

        System.out.println(JSON.toJSONString(userList));
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}

class User {
    int id;
    String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
