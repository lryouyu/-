package com.ysu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时任务
@EnableScheduling
//扫描Mapper接口，利用动态代理技术，根据接口，生成对应的实现类，并生成Bean对象，交给SpringBoot容器管理
@MapperScan("com.ysu.dao")
public class SpringBootMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class,args);
    }
}
