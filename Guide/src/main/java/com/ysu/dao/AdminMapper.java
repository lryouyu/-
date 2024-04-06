package com.ysu.dao;

import com.ysu.common.JsonResult;
import com.ysu.entity.Admin;
import com.ysu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    void insertAdmin(Admin admin);

    Integer deleteAdminById(Integer id);

    Integer updateAdmin(Admin admin);

    Admin selectAdminById(Integer id);

    List<Admin> selectByUsernameAndPassword(@Param("username") String username,
                                           @Param("password") String password);
}
