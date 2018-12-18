package com.ccq.springbootkafka.common.annotation;

import java.lang.annotation.*;

/********************************
 *** 自定义日志注解
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 10:59
 ***@Version 1.0.0
 ********************************/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {

    String value() default "日志注解";

}
