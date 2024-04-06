package com.ysu.controller;

import com.ysu.common.JsonResult;
import com.ysu.entity.Bus;
import com.ysu.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("bus")
public class BusController {
    @Autowired
    private BusService busService;

    @RequestMapping("selectById/{id}")
    public JsonResult selectById(@PathVariable("id") Integer id,HttpSession session){
        if (isAdminLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        Bus bus = busService.selectById(id);
        return new JsonResult<>(0,"",bus);
    }

    @RequestMapping("selectByName/{busName}")
    public JsonResult selectByName(@PathVariable("busName") String busName){
        List<Bus> busList = busService.selectByBusName(busName);
        if (busList.size()==0){
            return new JsonResult<>(0,"未查询到",null);
        }
        return new JsonResult<>(0,"",busList);
    }

    @RequestMapping("selectList")
    public JsonResult selectList(){
        List<Bus> busList = busService.selectList();
        return new JsonResult<>(0,"",busList);
    }


    @RequestMapping("selectByStation/{pathway}")
    public JsonResult selectByStation(@PathVariable("pathway") String pathway){
        List<Bus> busList = busService.selectListByStation(pathway);
        if (busList.size()==0){
            return new JsonResult<>(0,"未查询到",null);

        }
        return new JsonResult<>(0,"",busList);
    }

    @RequestMapping("insert")
    public JsonResult insert(Bus bus, HttpSession session) {
        if (isAdminLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        Integer id = busService.insert(bus);
        JsonResult result = new JsonResult<>(0, "", bus);
        return result;
    }

    @RequestMapping("delete/{id}")
    public JsonResult deleteById(@PathVariable("id")Integer id, HttpSession session) {
        if (isAdminLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        boolean b = busService.deleteById(id);
        if (b){
            return new JsonResult<>(0,"删除成功",true);
        }

        return new JsonResult<>(1,"删除失败",false);
    }

    @RequestMapping("update")
    public JsonResult update(@RequestBody Bus bus, HttpSession session) {
        if (isAdminLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        boolean b = busService.update(bus);
        if (b){
            return new JsonResult<>(0,"删除成功",true);
        }
        return new JsonResult<>(1,"删除失败",false);
    }

    boolean isAdminLogin(HttpSession session){
        if (session == null || session.getAttribute("admin") == null) {
            return true;
        }
        return false;
    }


}
