package com.ysu.dao;

import com.ysu.entity.Ticket;
import com.ysu.entity.UseTicket;

import java.util.List;

public interface TicketMapper {
    List<Ticket> selectListTicket();

    int updateTicket(Ticket ticket);

    Ticket selectTicketById(Integer id);

    void insertTicket(Ticket ticket);

    int deleteTicketById(Integer id);

    int deleteUseTicketById(Integer id);

    List<UseTicket> selectListUseTicket();

    List<UseTicket> selectUseTicketByTicketId(Integer ticketId);

    void buyUserTicket(UseTicket useTicket);

    List<Ticket> selectTicketsByAttId(Integer attId);

    UseTicket selectUseTicketById(int id);

    List<Ticket> selectTicketsByUserId(Integer userId);



}
