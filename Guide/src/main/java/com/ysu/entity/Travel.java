package com.ysu.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Travel {
    private Integer id;
    private String title;
    private String type;
    private String introduce;
    private String content;
    private String time;
    private Integer userId;
    private String imgPath;

}
