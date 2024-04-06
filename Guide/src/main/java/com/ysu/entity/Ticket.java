package com.ysu.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Ticket {
    private Integer id;
    private BigDecimal cost;
    private Integer attId;
    private String startTime;
    private String endTime;
    private String attName;
    private Integer count;
}
