package com.ysu.controller;


import com.ysu.common.JsonResult;
import com.ysu.entity.Admin;
import com.ysu.entity.Attraction;
import com.ysu.entity.User;
import com.ysu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //登录
    @RequestMapping("login")
    public JsonResult adminLogin(String username, String password, HttpSession session) {
        if (username == null || "".equals(username.trim()) || password == null || "".equals(password.trim())) {
            return new JsonResult(0, "用户名或密码不能为空", null);

        }
        JsonResult result = adminService.login(username, password);
        if (result.getState() == 0) {
            //登陆成功，放置登陆标记
            //springboot自动传session
            Admin admin = (Admin) result.getData();
            //在session中存储user对象，key为user字符串，value是user对象
            session.setAttribute("admin", admin);
        }
        //往session对下你个存储登陆过的标记
        return result;
    }

    @RequestMapping("logout")
    public JsonResult adminLoginOut(HttpSession session) {
        session.setAttribute("admin", null);
        if (session.getAttribute("admin")==null){
            return new JsonResult<>(0, "安全退出", true);
        }

        else {
            return new JsonResult<>(0, "退出失败", false);

        }
    }


    boolean isAdminLogin(HttpSession session){
        if (session == null || session.getAttribute("admin") == null) {
            return true;
        }
        return false;
    }

}
