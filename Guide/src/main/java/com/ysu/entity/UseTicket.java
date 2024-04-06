package com.ysu.entity;

import lombok.Data;

@Data
public class UseTicket {
    private Integer id;
    private Integer userId;
    private String time;
    private String username;
    private String useTime;
    private Integer ticketId;

}
