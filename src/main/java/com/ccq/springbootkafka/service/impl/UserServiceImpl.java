package com.ccq.springbootkafka.service.impl;

import com.alibaba.fastjson.JSON;
import com.ccq.springbootkafka.domain.User;
import com.ccq.springbootkafka.mapper.UserMapper;
import com.ccq.springbootkafka.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/20 10:28
 ***@Version 1.0.0
 ********************************/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User addUser(User user) {
        userMapper.addUser(user);
        return user;
    }

    @Override
    public boolean updateUser(Long id, User user) {
        user.setId(id);
        return userMapper.updateUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {

        log.info(id + "");
        return userMapper.deleteUser(id);
    }

    @Override
    public List<User> getUserList() {

        return userMapper.getUserList();
    }

    @Override
    public User getUserById(Long id) {

        return userMapper.getUserById(id);
    }
}
