package com.ysu.service;

import com.ysu.entity.Ticket;
import com.ysu.entity.UseTicket;

import java.util.List;

public interface TicketService {
    //管理查询所有的门票种类
    List<Ticket> selectListTicket();

    //管理员更新门票信息
    int updateTicket(Ticket ticket);

    //管理员新增门票信息
    Integer insertTicket(Ticket ticket);

    //管理员删除门票
    boolean deleteTicketById(Integer id);

    //管理员删除已有订单
    boolean deleteUseTicketById(Integer id);

    Ticket selectTicketById(Integer id);

    List<UseTicket> selectListUseTicket();

    List<UseTicket> selectUseTicketByTicketId(Integer ticketId);

    int buyUserTicket(UseTicket useTicket);

    UseTicket selectUseTicketById(int id);

    List<Ticket> selectTicketsByAttId(Integer attId);

    List<Ticket> selectTicketsByUserId(Integer userId);
}
