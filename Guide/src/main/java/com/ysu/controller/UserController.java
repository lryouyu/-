package com.ysu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.entity.Admin;
import com.ysu.entity.User;
import com.ysu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("insert")
    public JsonResult insert(User user,HttpSession session) {
        Integer id = userService.register(user);
        JsonResult result = new JsonResult<>(0, "", user);
        return result;
    }

    //登录
    @RequestMapping("login")
    public JsonResult userLogin(String username, String password, HttpSession session) {
        if (username == null || "".equals(username.trim()) || password == null || "".equals(password.trim())) {
            return new JsonResult(0, "用户名或密码不能为空", null);

        }
        JsonResult result = userService.login(username, password);
        if (result.getState() == 0) {
            //登陆成功，放置登陆标记
            //springboot自动传session
            User user = (User) result.getData();
            //在session中存储user对象，key为user字符串，value是user对象
            session.setAttribute("user", user);
        }
        //往session对下你个存储登陆过的标记
        return result;
    }
    @RequestMapping("logout")
    public JsonResult userLoginOut(HttpSession session) {
        session.setAttribute("user", null);
        if (session.getAttribute("admin")==null){
            return new JsonResult<>(0, "安全退出", true);
        }
        else {
            return new JsonResult<>(0, "退出失败", false);

        }
    }



    @RequestMapping("all")
    public JsonResult all(HttpSession session) {
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        List<User> users = userService.selectList();
        JsonResult result = new JsonResult<>(0, "", users);
        return result;
    }

    @RequestMapping("select/{username}")
    public JsonResult selectByUsername(@PathVariable("username") String username, HttpSession session) {
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        List<User> users = userService.selectByUsername(username);
        JsonResult result = new JsonResult<>(0, "", users);
        return result;
    }

    @RequestMapping("selectById/{id}")
    public JsonResult selectById(@PathVariable Integer id, HttpSession session) {
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        User user = userService.selectById(id);
        JsonResult result = new JsonResult<>(0, "", user);
        return result;
    }

    @RequestMapping("delete/{id}")
    public JsonResult delete(@PathVariable("id") Integer id, HttpSession session) {
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean bl = userService.deleteById(id);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "成功", null);
        } else {
            result = new JsonResult<>(1, "失败", null);
        }
        return result;
    }

    @RequestMapping("update")
    public JsonResult update(User user,HttpSession session) {
//        if (isUserLogin(session)&&isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        System.out.println(user);
        boolean bl = userService.update(user);
        JsonResult result;
        if (bl) {
            result = new JsonResult<>(0, "成功", null);
        } else {
            result = new JsonResult<>(1, "失败", null);
        }
        return result;
    }

    @RequestMapping("check")
    public JsonResult check(User user,HttpSession session) {
//        if (isUserLogin(session)&&isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        User user1 = userService.check(user);
        JsonResult result;
        if (user1!=null) {
            result = new JsonResult<>(0, "成功", user1);
        } else {
            result = new JsonResult<>(0, "失败", null);
        }
        return result;
    }

    boolean isAdminLogin(HttpSession session){
        if (session == null || session.getAttribute("admin") == null) {
            return true;
        }
        return false;
    }
    boolean isUserLogin(HttpSession session){
        if (session == null || session.getAttribute("user") == null) {
            return true;
        }
        return false;
    }

}
