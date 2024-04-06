package com.ysu.entity;

import lombok.Data;

import java.util.List;

@Data
public class Attraction {
    private Integer id;
    private String attName;
    private String introduce;
    private Integer star;
    private String service;
    private String startTime;
    private String endTime;
    private String address;
    private String tel;
    private String score;

}
