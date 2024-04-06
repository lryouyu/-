package com.ysu.interceptors;

import com.alibaba.fastjson2.JSONObject;
import com.ysu.common.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    //访问controller之前 先执行preHandle，所以可以作为是否登录的判断
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session对象
        HttpSession session = request.getSession();
        if (session==null || (session.getAttribute("user")==null && session.getAttribute("admin")==null)){
            //设置中文编码和返回值的类型
            response.setHeader("Content-Type","application/json;charset=utf-8");
            JsonResult result=new JsonResult<>(-1,"未登录或登陆过期，请重新登录",null);
            //吧result对象转换成json字符串
            String json= JSONObject.toJSONString(result);
            //把json的字符串输出到前端
            response.getWriter().write(json);
            //不放行，就意味着，不能再访问controller对应的方法了
            return false;
        }
        //放行
        return true;

    }
}
