package com.ccq.springbootkafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccq.springbootkafka.domain.Tree;
import com.ccq.springbootkafka.dto.response.model.Folder;
import com.ccq.springbootkafka.service.TreeService;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootkafkaApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    public TreeService treeService;

    /**
     * 递归查询节点路径
     */
    @Test
    public void getPath() {
        System.out.println(getPath(5L));
    }

    public String getPath(Long id) {
        if (id == 0) {
            return "";
        }
        Tree node = treeService.getTreeById(id);
        return getPath(node.getParentId()) + "/" + node.getName();
    }

    /**
     * 获取目录树
     */
    @Test
    public void getAll() {
        System.out.println(JSONObject.toJSONString(treeService.getAll(null)));
    }

    /**
     * 获取目录树递归实现
     */
    @Test
    public void getAll2() {
        System.out.println(JSONObject.toJSONString(getTree(0L)));
    }

    private List<Tree> getTree(Long pId) {
        List<Tree> childList = treeService.getByPid(pId);
        if (childList.size() == 0) {
            return null;
        }
        for (Tree tree : childList) {
            tree.setChilds(getTree(tree.getId()));
        }
        return childList;
    }

    /**
     * 使用排序加集合整理查询目录树
     */
    @Test
    public void getAll3() {
        System.out.println(JSONObject.toJSONString(getAll31()));
    }

    public List<Tree> getAll31() {
        List<Tree> nodeList = treeService.getAllTree();
        nodeList.sort(Comparator.comparing(Tree::getParentId).reversed().thenComparing(Tree::getWeight));
        Map<Long, Tree> nodeMap = nodeList.stream().collect(Collectors.toMap(Tree::getId, tree -> tree));
        Tree node;
        for (int i = 1, j = 0; i <= nodeList.size(); i++) {
            if (i == nodeList.size()) {
                return nodeList.subList(j, i);
            }
            if (!nodeList.get(j).getParentId().equals(nodeList.get(i).getParentId())) {
                node = nodeMap.get(nodeList.get(j).getParentId());
                if (node != null) {
                    node.setChilds(nodeList.subList(j, i));
                }
                j = i;
            }

        }
        return new ArrayList<>();
    }
}
