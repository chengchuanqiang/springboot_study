package com.ccq.springbootkafka.common.annotation;

import java.lang.annotation.*;

/********************************
 *** 水果颜色注解
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 10:12
 ***@Version 1.0.0
 ********************************/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    /**
     * 颜色枚举
     */
    public enum Color {
        BLUE, RED, GREEN
    }

    /**
     * 颜色属性
     *
     * @return string
     */
    Color fruitColor() default Color.GREEN;

}
