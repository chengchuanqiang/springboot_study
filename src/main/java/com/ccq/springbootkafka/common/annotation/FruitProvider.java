package com.ccq.springbootkafka.common.annotation;

import java.lang.annotation.*;

/********************************
 *** 水果供应商注解
 ***@Author chengchuanqiang
 ***@Date 2018/9/21 10:20
 ***@Version 1.0.0
 ********************************/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {

    /**
     * 供应商编号
     *
     * @return id
     */
    public int id() default -1;

    /**
     * 供应商名称
     *
     * @return name
     */
    public String name() default "";

    /**
     * 供应商地址
     *
     * @return address
     */
    public String address() default "";

}
