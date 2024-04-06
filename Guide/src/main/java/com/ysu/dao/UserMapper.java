package com.ysu.dao;

import com.ysu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    void insert(User user);
    List<User> selectByUsernameAndPassword(@Param("username") String username,
                                           @Param("password") String password);
    List<User> selectByUsername(String username);

    User selectById(Integer id);

    List<User> selectList();

    int deleteById(Integer id);

    int update(User user);

    User check(User user);
}
