package com.geektime.seven.controller;

import com.geektime.seven.entity.User;
import com.geektime.seven.mapper.UserMapper;
import com.geektime.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    DataSource dataSource;

    @RequestMapping("start")
    @ResponseBody
    public User start(){
        User user = userService.getUserById(1);
        System.out.println(user);
        return user;
    }

    @RequestMapping("save")
    @ResponseBody
    public User save(){
        User user = new User();
        user.setBirthday("1990-01-01");
        user.setCreateTime(System.currentTimeMillis());
        user.setLastUpdateTime(System.currentTimeMillis());
        user.setEmail("1@126.com");
        user.setName("wangwu");
        user.setPassword("wangwu");
        user.setTelphone("13555555555");
        userService.save(user);
        return user;
    }
}
