package com.ysu.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.dao.TravelMapper;
import com.ysu.entity.Comment;
import com.ysu.entity.Strategy;
import com.ysu.entity.Travel;
import com.ysu.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("travel")
public class TravelController {
    @Autowired
    private TravelService travelService;

    @RequestMapping("insertTravel")
    public JsonResult insertTravel(Travel travel, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        travel.setTime(String.valueOf(new Date()));
        Integer id = travelService.insertTravel(travel);
        if (id > 0) {
            return new JsonResult<>(0, "新增成功", travel);
        } else {
            return new JsonResult<>(0,"新增失败",null);
        }
    }

    @RequestMapping("selectListTravel")
    public JsonResult selectListTravel(){
        List<Travel> travel = travelService.selectListTravel();
        JsonResult result = new JsonResult<>(0, "", travel);
        return result;
    }


    @RequestMapping("selectByTitle/{title}")
    public JsonResult selectByTitle(@PathVariable("title") String title){
        List<Travel> travels = travelService.selectByTitle(title);
        JsonResult result = new JsonResult<>(0, "", travels);
        return result;
    }

    @RequestMapping("selectByType/{type}")
    public JsonResult selectByType(@PathVariable("type") String type){
        List<Travel> travels = travelService.selectByType(type);
        JsonResult result = new JsonResult<>(0, "", travels);
        return result;
    }

    @RequestMapping("selectTravelById/{id}")
    public JsonResult selectTravelById(@PathVariable("id") Integer id){
        Travel travel = travelService.selectTravelById(id);
        return new JsonResult<>(0, "", travel);
    }

    @RequestMapping("selectTravelByUserId/{userId}")
    public JsonResult selectTravelByUserId(@PathVariable("userId") Integer userId){
        List<Travel> travels = travelService.selectTravelByUserId(userId);
        return new JsonResult<>(0, "", travels);
    }

    @RequestMapping("deleteTravelById/{id}")
    public JsonResult deleteTravelById(@PathVariable("id") Integer id,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = travelService.deleteTravelById(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(1,"删除失败",false);
        }
    }

    @RequestMapping("updateTravel")
    public JsonResult updateTravel(@RequestBody Travel travel){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = travelService.updateTravel(travel);
        if (b) {
            return new JsonResult<>(0, "更新成功", selectTravelById(travel.getId()));
        } else {
            return new JsonResult<>(1,"更新失败",null);
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
