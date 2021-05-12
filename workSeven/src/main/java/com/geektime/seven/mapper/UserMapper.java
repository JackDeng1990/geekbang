package com.geektime.seven.mapper;

import com.geektime.seven.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "createTime",  column = "create_time"),
            @Result(property = "lastUpdateTime", column = "last_update_time")
    })
    List<User> getAll();
}
