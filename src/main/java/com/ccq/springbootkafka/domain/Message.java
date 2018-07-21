package com.ccq.springbootkafka.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

/********************************
 *** 消息实体类
 ***@Author chengchuanqiang
 ***@Date 2018/7/16 17:18
 ***@Version 1.0.0
 ********************************/
@Data
//@ApiModel(value = "消息实体类", description = "消息实体类")
public class Message {

    @ApiParam(value = "消息主键")
    @ApiModelProperty(value = "消息主键", example = "11", dataType = "Integer")
    private int id;

    @ApiModelProperty(value = "消息内容", example = "hello kafka", dataType = "string")
    private String msg;

    @ApiModelProperty(notes = "创建时间 (时间格式;yyyy-MM-dd HH:mm:ss)", dataType = "string")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ApiModelProperty(value = "分数", example = "11.22", dataType = "string")
    private double score;

}
