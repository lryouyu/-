package com.ysu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.dao.TicketMapper;
import com.ysu.entity.*;
import com.ysu.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @RequestMapping("selectListTicket")
    public JsonResult selectListTicket(HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        List<Ticket> tickets = ticketService.selectListTicket();
        return new JsonResult<>(0,"查询成功",tickets);
    }

    @RequestMapping("selectListUseTicket")
    public JsonResult selectListUseTicket(HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        List<UseTicket> tickets = ticketService.selectListUseTicket();
        return new JsonResult<>(0,"查询成功",tickets);
    }

    @RequestMapping("selectUseTicketByTicketId/{ticketId}")
    public JsonResult selectUseTicketByTicketId(@PathVariable("ticketId") Integer ticketId, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        List<UseTicket> tickets = ticketService.selectUseTicketByTicketId(ticketId);
        return new JsonResult<>(0,"查询成功",tickets);
    }

    @RequestMapping("selectTicketsByAttId/{attId}")
    public JsonResult selectTicketsByAttId(@PathVariable("attId") Integer attId, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        List<Ticket> tickets = ticketService.selectTicketsByAttId(attId);
        return new JsonResult<>(0,"查询成功",tickets);
    }

    @RequestMapping("selectTicketsByUserId/{userId}")
    public JsonResult selectTicketsByUserId(@PathVariable("userId") Integer userId, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        List<Ticket> tickets = ticketService.selectTicketsByUserId(userId);
        return new JsonResult<>(0,"查询成功",tickets);
    }




    @RequestMapping("selectTicketById/{id}")
    public JsonResult selectTicketById(@PathVariable("id") Integer id, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        Ticket ticket = ticketService.selectTicketById(id);
        return new JsonResult<>(0,"查询成功",ticket);
    }

    @RequestMapping("updateTicket")
    public JsonResult updateTicket(Ticket ticket,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        int rows = ticketService.updateTicket(ticket);
        if (rows > 0) {
            return new JsonResult<>(0, "更新成功", ticketService.selectTicketById(ticket.getId()));
        } else {
            return new JsonResult<>(1, "更新失败", null);
        }
    }

    @RequestMapping("insert")
    public JsonResult insert(Ticket ticket,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        int id = ticketService.insertTicket(ticket);
        if (id > 0) {
            return new JsonResult<>(0, "新增成功", ticketService.selectTicketById(id));
        } else {
            return new JsonResult<>(0, "新增失败", null);
        }
    }

    @RequestMapping("buyUserTicket")
    public JsonResult buyUserTicket(UseTicket useTicket,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        useTicket.setTime(String.valueOf(new Date()));
        int id = ticketService.buyUserTicket(useTicket);
        System.out.println(id);
        if (id > 0) {
            return new JsonResult<>(0, "新增成功", ticketService.selectUseTicketById(id));
        } else {
            return new JsonResult<>(0, "新增失败", null);
        }
    }

    @RequestMapping("deleteTicketById/{id}")
    public JsonResult deleteTicketById(@PathVariable("id") Integer id,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = ticketService.deleteTicketById(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(0, "删除失败", false);
        }
    }

    @RequestMapping("deleteUseTicketById/{id}")
    public JsonResult deleteUseTicketById(@PathVariable("id") Integer id,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = ticketService.deleteUseTicketById(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(0, "删除失败", false);
        }
    }




    boolean isAdminLogin(HttpSession session){
        if (session == null || session.getAttribute("admin") == null) {
            return true;
        }
        return false;
    }
    boolean isUserLogin(HttpSession session){
        if (session == null || session.getAttribute("user") == null) {
            return true;
        }
        return false;
    }

}
