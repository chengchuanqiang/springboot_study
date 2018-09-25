package com.ccq.springbootkafka.common.annotation;

import java.lang.annotation.*;

/********************************
 *** 水果名称注解
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 10:09
 ***@Version 1.0.0
 ********************************/

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {

    String value() default "";
}
