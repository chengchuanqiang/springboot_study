package com.ccq.springbootkafka.dto.response.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/30 17:22
 ***@Version 1.0.0
 ********************************/
@Data
public class Folder implements Serializable {

    private Long id;
    private String name;
    private String type;
    private Long parentId;
    private Integer weight;
    private List<Folder> subFolders;

}
