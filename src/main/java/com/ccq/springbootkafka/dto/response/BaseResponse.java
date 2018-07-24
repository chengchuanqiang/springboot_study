package com.ccq.springbootkafka.dto.response;

import com.ccq.springbootkafka.dto.enums.ResponseInfoType;
import lombok.Data;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/24 15:22
 ***@Version 1.0.0
 ********************************/
@Data
public class BaseResponse<T> {

    private String msg;
    private Integer code;
    private T data;

    public BaseResponse(String msg, Integer code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static<T> BaseResponse successInstance(T data) {
        return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), data);
    }

    public static<T> BaseResponse failInstance(int code, String msg) {
        return new BaseResponse<>(msg, code,null);
    }

}
