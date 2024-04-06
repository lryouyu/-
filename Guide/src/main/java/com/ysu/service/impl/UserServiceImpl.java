package com.ysu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.dao.UserMapper;
import com.ysu.entity.User;
import com.ysu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public JsonResult login(String username, String password) {
        List<User> users = userMapper.selectByUsernameAndPassword(username, password);
        if (users.size()==0){
            return new JsonResult(0,"用户名或密码错误",null);
        }
        return new JsonResult(0,"",users.get(0));

    }

    @Override
    public Integer register(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public List<User> selectByUsername(String username) {
        List<User> users = userMapper.selectByUsername(username);
        return users;
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User check(User user) {
        return userMapper.check(user);
    }

    @Override
    public List<User> selectList() {
        List<User> users = userMapper.selectList();
        return users;
    }

    @Override
    public boolean deleteById(Integer id) {
        int rows = userMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public boolean update(User user) {
        int rows = userMapper.update(user);
        return rows > 0;
    }

}
