package com.ccq.springbootkafka.service;

import com.ccq.springbootkafka.domain.Tree;
import com.ccq.springbootkafka.dto.response.model.Folder;

import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/30 17:18
 ***@Version 1.0.0
 ********************************/
public interface TreeService {


    List<Folder> getAll(String filterStr);

    List<Tree> getAllTree();

    Tree getTreeById(Long id);

    List<Tree> getByPid(Long pId);
}
