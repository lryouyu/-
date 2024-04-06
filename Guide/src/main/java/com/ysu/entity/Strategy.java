package com.ysu.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Strategy {
    private Integer id;
    private String title;
    //攻略种类
    private String type;
    //攻略简介
    private String introduce;
    //攻略内容
    private String content;
    //发布时间
    private Date time;
    //点赞数
    private Integer like = 0;
    private Integer adminId;
    private String warn;
    private String traffic;

}
