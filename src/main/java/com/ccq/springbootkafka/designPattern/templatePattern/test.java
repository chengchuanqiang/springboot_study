package com.ccq.springbootkafka.designPattern.templatePattern;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/21 11:00
 ***@Version 1.0.0
 ********************************/
public class test {

    public static void main(String[] args) {

        Game game = new Basketball();
        game.play();
        System.out.println();

        game = new Football();
        game.play();
    }
}
