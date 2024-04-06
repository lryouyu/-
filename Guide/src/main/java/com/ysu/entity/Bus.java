package com.ysu.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Data
public class Bus {
    private Integer id;
    private String busName;
    private String start;
    private String end;
    private String startTime;
    private String endTime;
    private String pathway;

}
