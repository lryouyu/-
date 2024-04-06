package com.ysu.entity;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Hotel {
    private Integer id;
    private String hotelName;
    private String address;
    private String content;
    private BigDecimal score;
    private String tel;

}
