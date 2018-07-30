package com.ccq.springbootkafka.mapper;

import com.ccq.springbootkafka.domain.Tree;
import com.ccq.springbootkafka.domain.TreeExample;
import java.util.List;

import com.ccq.springbootkafka.dto.response.model.Folder;
import org.apache.ibatis.annotations.Param;

public interface TreeMapper {
    int countByExample(TreeExample example);

    int deleteByExample(TreeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Tree record);

    int insertSelective(Tree record);

    List<Tree> selectByExample(TreeExample example);

    Tree selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Tree record, @Param("example") TreeExample example);

    int updateByExample(@Param("record") Tree record, @Param("example") TreeExample example);

    int updateByPrimaryKeySelective(Tree record);

    int updateByPrimaryKey(Tree record);

    List<Folder> getAll(@Param("filterStr") String filterStr);
}