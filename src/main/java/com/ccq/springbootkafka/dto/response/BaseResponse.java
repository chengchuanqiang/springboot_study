package com.ccq.springbootkafka.dto.response;

/********************************
 *** 基础返回对象
 ***@Author chengchuanqiang
 ***@Date 2018/7/20 10:11
 ***@Version 1.0.0
 ********************************/
public class BaseResponse {

    private String msg = "";
    private Integer code;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseResponse(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public BaseResponse() {
    }

    public BaseResponse(String msg, Integer code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
