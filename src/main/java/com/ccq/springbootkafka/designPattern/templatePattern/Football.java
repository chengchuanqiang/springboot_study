package com.ccq.springbootkafka.designPattern.templatePattern;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/21 10:59
 ***@Version 1.0.0
 ********************************/
public class Football extends Game {
    @Override
    void init() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void operation() {

    }

    @Override
    boolean isOperation() {
        return false;
    }
}
