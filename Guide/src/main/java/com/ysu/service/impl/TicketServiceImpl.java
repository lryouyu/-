package com.ysu.service.impl;

import com.ysu.dao.TicketMapper;
import com.ysu.entity.Ticket;
import com.ysu.entity.UseTicket;
import com.ysu.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;


    @Override
    public List<Ticket> selectListTicket() {
        return ticketMapper.selectListTicket();
    }

    @Override
    public int updateTicket(Ticket ticket) {
        return ticketMapper.updateTicket(ticket);
    }

    @Override
    public Integer insertTicket(Ticket ticket) {
        ticketMapper.insertTicket(ticket);
        return ticket.getId();
    }

    @Override
    public boolean deleteTicketById(Integer id) {
        int rows = ticketMapper.deleteTicketById(id);
        return rows > 0;
    }

    @Override
    public boolean deleteUseTicketById(Integer id) {
        int rows = ticketMapper.deleteUseTicketById(id);
        return rows > 0;
    }

    @Override
    public Ticket selectTicketById(Integer id) {
        return ticketMapper.selectTicketById(id);
    }

    @Override
    public List<UseTicket> selectListUseTicket() {
        return ticketMapper.selectListUseTicket();
    }

    @Override
    public List<UseTicket> selectUseTicketByTicketId(Integer ticketId) {
        return ticketMapper.selectUseTicketByTicketId(ticketId);
    }

    @Override
    public int buyUserTicket(UseTicket useTicket) {
        ticketMapper.buyUserTicket(useTicket);
        return useTicket.getId();
    }

    @Override
    public UseTicket selectUseTicketById(int id) {
        return ticketMapper.selectUseTicketById(id);
    }

    @Override
    public List<Ticket> selectTicketsByAttId(Integer attId) {
        return ticketMapper.selectTicketsByAttId(attId);
    }

    @Override
    public List<Ticket> selectTicketsByUserId(Integer userId) {
        return ticketMapper.selectTicketsByUserId(userId);
    }
}
