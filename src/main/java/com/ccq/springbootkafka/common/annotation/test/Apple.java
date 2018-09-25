package com.ccq.springbootkafka.common.annotation.test;

import com.ccq.springbootkafka.common.annotation.FruitColor;
import com.ccq.springbootkafka.common.annotation.FruitName;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 10:16
 ***@Version 1.0.0
 ********************************/
public class Apple {

    @FruitName("apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public void displayName() {
        System.out.println("水果的名称是：苹果");
    }
}
