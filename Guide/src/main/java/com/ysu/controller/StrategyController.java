package com.ysu.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.entity.Comment;
import com.ysu.entity.Hotel;
import com.ysu.entity.Strategy;
import com.ysu.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("strategy")
public class StrategyController {
    @Autowired
    private StrategyService strategyService;

    @RequestMapping("insertStrategy")
    public JsonResult insertStrategy(Strategy strategy, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        strategy.setTime(new Date());
        System.out.println(strategy);
        Integer id = strategyService.insertStrategy(strategy);
        if (id > 0) {
            return new JsonResult<>(0, "新增成功", strategy);
        } else {
            return new JsonResult<>(1,"新增失败",null);
        }
    }

    @RequestMapping("selectListStrategy")
    public JsonResult selectListStrategy(){
        List<Strategy> strategy = strategyService.selectListStrategy();
        JsonResult result = new JsonResult<>(0, "", strategy);
        return result;
    }

    @RequestMapping("selectStrategyTop")
    public JsonResult selectStrategyTop(){
        List<Strategy> strategy = strategyService.selectStrategyTop();
        JsonResult result = new JsonResult<>(0, "", strategy);
        return result;
    }


    @RequestMapping("selectByTitle/{title}")
    public JsonResult selectByTitle(@PathVariable("title") String title){
        List<Strategy> strategies = strategyService.selectByTitle(title);
        JsonResult result = new JsonResult<>(0, "", strategies);
        return result;
    }

    @RequestMapping("selectByType/{type}")
    public JsonResult selectByType(@PathVariable("type") String type){
        List<Strategy> strategies = strategyService.selectByType(type);
        JsonResult result = new JsonResult<>(0, "", strategies);
        return result;
    }

    @RequestMapping("updateStrategy")
    public JsonResult updateStrategy(@RequestBody Strategy strategy, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        strategy.setTime(new Date());
        boolean b = strategyService.updateStrategy(strategy);
        if (b) {
            return new JsonResult<>(0, "更新成功", selectStrategyById(strategy.getId()));
        } else {
            return new JsonResult<>(1,"更新失败",null);
        }
    }

    @RequestMapping("selectStrategyById/{id}")
    public JsonResult selectStrategyById(@PathVariable("id") Integer id){
        Strategy strategy = strategyService.selectStrategyById(id);
        return new JsonResult<>(0, "", strategy);
    }

    @RequestMapping("deleteStrategyById/{id}")
    public JsonResult deleteStrategyById(@PathVariable("id") Integer id,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = strategyService.deleteStrategyById(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(0,"删除失败",false);
        }
    }

    @RequestMapping("insertComment")
    public JsonResult insertComment(Comment comment, HttpSession session){
        if (isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        comment.setTime(String.valueOf(new Date()));
        Integer id = strategyService.insertComment(comment);

        if (id > 0) {
            return new JsonResult<>(0, "新增成功", comment);
        } else {
            return new JsonResult<>(1,"新增失败",null);
        }
    }

    @RequestMapping("deleteCommentById/{id}")
    public JsonResult deleteComment(@PathVariable("id") Integer id,HttpSession session){
        if (isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        boolean b = strategyService.deleteComment(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(1,"删除失败",false);
        }
    }
    @RequestMapping("selectListCommentById/{strId}")
    public JsonResult selectListCommentById(@PathVariable("strId") Integer strId){
        List<Comment> comments = strategyService.selectListCommentById(strId);
        JsonResult result = new JsonResult<>(0, "", comments);
        return result;
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
