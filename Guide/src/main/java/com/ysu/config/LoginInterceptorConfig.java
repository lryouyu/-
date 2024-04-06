package com.ysu.config;

import com.ysu.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//配置自定义的登陆拦截器
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    //添加自定义的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对所有路径进行拦截
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/**").
                //排除掉哪些url
                excludePathPatterns("/user/login","/admin/login","/user/check","/user/update","/hotel/**","/upload/**","/image/**","/user/insert");
    }
}
