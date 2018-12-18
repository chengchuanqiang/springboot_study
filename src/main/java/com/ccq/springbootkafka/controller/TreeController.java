package com.ccq.springbootkafka.controller;

import com.ccq.springbootkafka.dto.enums.ResponseInfoType;
import com.ccq.springbootkafka.dto.response.BaseResponse;
import com.ccq.springbootkafka.dto.response.model.Folder;
import com.ccq.springbootkafka.service.TreeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/30 17:33
 ***@Version 1.0.0
 ********************************/

@RestController
@RequestMapping("tree")
@Slf4j
public class TreeController {

    @Autowired
    private TreeService treeService;

    @ApiOperation(value = "查询工作表目录结构", notes = "目录结构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filterStr", value = "搜索字符串", required = false, dataType = "string", example = "1"),
    })
    @PostMapping("index")
    public BaseResponse<List<Folder>> getAll(String filterStr) {
        try {
            List<Folder> folderList =  treeService.getAll(filterStr);
            return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), folderList);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new BaseResponse<>(ResponseInfoType.ERROR1.getMsg(), ResponseInfoType.ERROR1.getCode(), null);
        }
    }

}
