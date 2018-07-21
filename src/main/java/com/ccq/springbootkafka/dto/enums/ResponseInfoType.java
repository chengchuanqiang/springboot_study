package com.ccq.springbootkafka.dto.enums;

/********************************
 *** 返回异常枚举类
 ***@Author chengchuanqiang
 ***@Date 2018/7/20 10:13
 ***@Version 1.0.0
 ********************************/
public enum ResponseInfoType {
    SUCCESS(0, "SUCCESS"),
    ERROR1(100, "系统异常"),
    ERROR2(200, "参数异常"),
    ERROR3(300, "数据异常"),
    ERROR4(400, "业务异常");

    private Integer code;
    private String msg;

    ResponseInfoType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
