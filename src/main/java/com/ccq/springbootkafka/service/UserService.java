package com.ccq.springbootkafka.service;

import com.ccq.springbootkafka.domain.User;

import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/7/20 10:27
 ***@Version 1.0.0
 ********************************/
public interface UserService {

    User addUser(User user);

    boolean updateUser(Long id ,User user);

    boolean deleteUser(Long id);

    List<User> getUserList();

    User getUserById(Long id);
}
