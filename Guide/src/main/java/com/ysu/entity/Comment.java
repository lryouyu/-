package com.ysu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;
    //攻略回复内容
    private String content;
    private String time;
    private Integer strId;
    private Integer userId;

}
