package com.geektime.seven.service;

import com.geektime.seven.entity.User;

public interface UserService {
    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public User getUserById(Integer id);

    /**
     * 保存用户
     * @param user
     */
    public void save(User user);
}
