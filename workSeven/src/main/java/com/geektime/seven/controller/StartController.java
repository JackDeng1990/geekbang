package com.geektime.seven.controller;

import com.geektime.seven.entity.User;
import com.geektime.seven.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.List;

@RestController
public class StartController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    DataSource dataSource;

    @RequestMapping("start")
    @ResponseBody
    public User start(){
        System.out.println("DATASOURCE = " + dataSource);
        List<User> users = userMapper.getAll();
        return users.get(0);
    }

}
