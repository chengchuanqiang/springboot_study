package com.ccq.springbootkafka.controller;

import com.ccq.springbootkafka.domain.User;
import com.ccq.springbootkafka.dto.enums.ResponseInfoType;
import com.ccq.springbootkafka.dto.response.BaseResponse;
import com.ccq.springbootkafka.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/********************************
 *** 测试用户提供的接口，结合swagger使用
 ***@Author chengchuanqiang
 ***@Date 2018/7/20 10:20
 ***@Version 1.0.0
 ********************************/
@RestController
@Slf4j
@Api(value = "用户接口", description = "用户接口操作入出参数api文档")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "user", value = "用户详细实体类", required = true, paramType = "body", dataType = "User")
    )
    @PostMapping("user")
    public BaseResponse<User> addUser(@RequestBody User user) {
        try {
            user = userService.addUser(user);
            return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), user);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new BaseResponse<>(ResponseInfoType.ERROR1.getMsg(), ResponseInfoType.ERROR1.getCode(), null);
        }
    }

    @ApiOperation(value = "修改用户", notes = "根据用户id修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Long", required = true, paramType = "path", example = "1"),
            @ApiImplicitParam(name = "user", value = "用户信息", dataType = "User", required = true, paramType = "body")
    })
    @PutMapping("user/{id}")
    public BaseResponse<Boolean> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            boolean isOk = userService.updateUser(id, user);
            return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), isOk);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new BaseResponse<>(ResponseInfoType.ERROR1.getMsg(), ResponseInfoType.ERROR1.getCode(), null);
        }
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Long", required = true, paramType = "path", example = "1")
    })
    @DeleteMapping("user/{id}")
    public BaseResponse<Boolean> deleteUser(@PathVariable("id") Long id) {
        try {
            boolean isDelete = userService.deleteUser(id);
            return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), isDelete);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new BaseResponse<>(ResponseInfoType.ERROR1.getMsg(), ResponseInfoType.ERROR1.getCode(), false);
        }
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("users")
    public BaseResponse<List<User>> getUserList() {
        try {
            List<User> userList = userService.getUserList();
            return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), userList);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new BaseResponse<>(ResponseInfoType.ERROR1.getMsg(), ResponseInfoType.ERROR1.getCode(), null);
        }
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据用户id获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Long", required = true, paramType = "path", example = "1")
    @GetMapping("user/{id}")
    public BaseResponse<User> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), user);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new BaseResponse<>(ResponseInfoType.ERROR1.getMsg(), ResponseInfoType.ERROR1.getCode(), null);
        }
    }

    /**
     * 使用注解隐藏这个Controller
     *
     * @return success
     */
    @ApiIgnore
    @GetMapping("ignore")
    public String ignoreTest() {
        return "success";
    }

}
