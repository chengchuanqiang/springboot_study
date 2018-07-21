package com.ccq.springbootkafka.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/********************************
 *** 用户实体类
 ***@Author chengchuanqiang
 ***@Date 2018/7/20 10:23
 ***@Version 1.0.0
 ********************************/
@Data
public class User {

    @ApiModelProperty(value = "用户主键", dataType = "Long", hidden = true)
    private Long id;

    @ApiModelProperty(value = "用户名", dataType = "string", example = "ccq")
    private String username;

    @ApiModelProperty(value = "年龄", dataType = "integer", example = "22")
    private Integer age;

    @ApiModelProperty(value = "创建时间", dataType = "string", hidden = true)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", dataType = "string", hidden = true)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public User(Long id, String username, Integer age, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {}
}
