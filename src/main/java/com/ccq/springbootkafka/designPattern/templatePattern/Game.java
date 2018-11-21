package com.ccq.springbootkafka.designPattern.templatePattern;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/21 10:55
 ***@Version 1.0.0
 ********************************/
public abstract class Game {
    abstract void init();

    abstract void startPlay();

    abstract void endPlay();

    // 钩子方法
    abstract void operation();

    boolean isOperation() {
        return true;
    }

    public final void play() {
        // 初始化游戏
        init();

        // 开始游戏
        startPlay();

        if (isOperation()) {
            operation();
        }

        // 结束游戏
        endPlay();
    }

}
