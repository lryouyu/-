package com.ysu.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class House {
    private Integer id;
    private String houseName;
    private BigDecimal cost;
    private Integer hotelId;
    private Integer personCount;
    private Integer num;
    private String introduce;
}
