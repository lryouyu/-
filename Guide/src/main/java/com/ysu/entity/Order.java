package com.ysu.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Integer id;
    private Date time;
    //入住时间
    private String inTime;
    //离开时间
    private String outTime;
    private Integer houseId;
    private String hotelName;
    private String houseName;
    private Integer userId;
    //付款
    private BigDecimal cost;
    private String username;
}
