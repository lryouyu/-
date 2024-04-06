package com.ysu.service;

import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.entity.User;

import java.util.List;

public interface UserService {
    //用户登录
    JsonResult login(String username, String password);


    //用户注册
    Integer register(User user);

    //用户信息显示
    List<User> selectByUsername(String username);

    //显示全部用户
    List<User> selectList();

    boolean deleteById(Integer id);

    boolean update(User user);

    User selectById(Integer id);


    User check(User user);
}
