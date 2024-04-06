package com.ysu.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String imgPath;
    private String realName;
    private String tel;
    private Integer age;
    //0是女 1是男
    private Integer sex;
    private String card;

}
