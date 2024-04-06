package com.ysu.service;

import com.ysu.common.JsonResult;
import com.ysu.entity.Admin;
import com.ysu.entity.User;

import java.util.List;

public interface AdminService {
    Integer insertAdmin(Admin admin);

    boolean deleteAdminById(Integer id);

    boolean updateAdmin(Admin admin);

    Admin selectAdminById(Integer id);
    JsonResult login(String username, String password);

}
