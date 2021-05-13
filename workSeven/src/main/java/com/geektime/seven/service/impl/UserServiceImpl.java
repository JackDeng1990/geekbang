package com.geektime.seven.service.impl;

import com.geektime.seven.config.DBContextHolder;
import com.geektime.seven.entity.User;
import com.geektime.seven.mapper.UserMapper;
import com.geektime.seven.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        //DBContextHolder.slave();
        return userMapper.getUserById(id);
    }

    @Override
    public void save(User user) {
        //DBContextHolder.master();
        userMapper.save(user);
    }
}
