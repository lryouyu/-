package com.ysu.service.impl;

import com.ysu.common.JsonResult;
import com.ysu.dao.AdminMapper;
import com.ysu.entity.Admin;
import com.ysu.entity.User;
import com.ysu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Integer insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);
        return admin.getId();
    }

    @Override
    public boolean deleteAdminById(Integer id) {
        int rows = adminMapper.deleteAdminById(id);
        return rows > 0;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        int rows = adminMapper.updateAdmin(admin);
        return rows > 0;
    }

    @Override
    public Admin selectAdminById(Integer id) {
        return adminMapper.selectAdminById(id);
    }

    @Override
    public JsonResult login(String username, String password) {
        List<Admin> admins = adminMapper.selectByUsernameAndPassword(username, password);
        System.out.println(admins.size());
        if (admins.size()==0){
            return new JsonResult(0,"用户名或密码错误",null);
        }
        return new JsonResult(0,"",admins.get(0));
    }


}
