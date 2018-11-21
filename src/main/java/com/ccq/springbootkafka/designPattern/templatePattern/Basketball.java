package com.ccq.springbootkafka.designPattern.templatePattern;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/21 10:58
 ***@Version 1.0.0
 ********************************/
public class Basketball extends Game {


    @Override
    void init() {
        System.out.println("Basketball Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Basketball Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Basketball Game Finished!");
    }

    @Override
    void operation() {
        System.out.println("Basketball Game Operation!");
    }
}
