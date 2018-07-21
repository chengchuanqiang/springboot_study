package com.ccq.springbootkafka.mapper;

import com.ccq.springbootkafka.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/********************************
 *** User的mapper类
 ***@Author chengchuanqiang
 ***@Date 2018/7/20 15:52
 ***@Version 1.0.0
 ********************************/
public interface UserMapper {


    void addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(@Param("id") Long id);

    List<User> getUserList();

    User getUserById(@Param("id") Long id);

}
