package com.ccq.springbootkafka.service;

import com.ccq.springbootkafka.domain.User;
import com.ccq.springbootkafka.mapper.UserMapper;
import com.ccq.springbootkafka.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/********************************
 *** mockito框架的使用
 *** http://www.cnblogs.com/csonezp/p/7868127.html
 ***@Author chengchuanqiang
 ***@Date 2018/12/27 14:09
 ***@Version 1.0.0
 ********************************/
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl();

    /**
     * 被@Mock标注的对象会自动注入到被@InjectMocks标注的对象中
     */
    @Mock
    private UserMapper userMapper;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        User user = new User();
        user.setId(2L);
        user.setAge(22);
        user.setUserName("test111");
        when(userService.getUserById(anyLong())).thenReturn(user);
    }


    @Test
    public void testAdd() {
        User user = userService.getUserById(2L);
        System.out.println(user);
        Assert.assertEquals("test111", user.getUserName());
    }
}
