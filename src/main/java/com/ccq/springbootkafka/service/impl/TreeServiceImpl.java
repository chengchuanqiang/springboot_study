package com.ccq.springbootkafka.service.impl;

import com.ccq.springbootkafka.domain.Tree;
import com.ccq.springbootkafka.dto.response.model.Folder;
import com.ccq.springbootkafka.mapper.TreeMapper;
import com.ccq.springbootkafka.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/30 17:40
 ***@Version 1.0.0
 ********************************/
@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeMapper treeMapper;

    @Override
    public List<Folder> getAll(String filterStr) {
        List<Folder> folderList = treeMapper.getAll(filterStr);

        folderList = folderList.stream().sorted((folder1, folder2) -> folder1.getParentId() < folder2.getParentId() ? 1 :
                (folder1.getParentId() > folder2.getParentId() ? -1 : folder1.getWeight() - folder2.getWeight())).collect(Collectors.toList());

        Map<Long, Folder> folderMap = folderList.stream().collect(Collectors.toMap(Folder::getId, folder -> folder));

        Folder folder = null;
        for(int i = 1, j =0; i <= folderList.size(); i++){
            if(i < folderList.size()){
                if(!folderList.get(j).getParentId().equals(folderList.get(i).getParentId())){
                    folder = folderMap.get(folderList.get(j).getParentId());
                    if(folder != null){
                        folder.setSubFolders(folderList.subList(j,i));
                    }
                    j = i;
                }
            }else{
                return folderList.subList(j,i);
            }
        }

        return new ArrayList<>();
    }
}
