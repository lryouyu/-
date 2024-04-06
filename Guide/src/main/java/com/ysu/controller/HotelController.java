package com.ysu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.entity.*;
import com.ysu.service.HotelService;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @RequestMapping("selectList")
    public JsonResult selectListHotel(){
        List<Hotel> hotels = hotelService.selectListHotel();
        JsonResult result = new JsonResult<>(0, "", hotels);
        return result;
    }

    @RequestMapping("selectListOrder")
    public JsonResult selectListOrder(HttpSession session){
        if (isAdminLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        List<Order> orders = hotelService.selectListOrder();
        JsonResult result = new JsonResult<>(0, "", orders);
        return result;
    }

    @RequestMapping("selectListOrderByUserId/{id}")
    public JsonResult selectListOrderByUserId(@PathVariable("id") Integer userId, HttpSession session){
        if (isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        List<Order> orders = hotelService.selectListOrderByUserId(userId);
        JsonResult result = new JsonResult<>(0, "", orders);
        return result;
    }

    @RequestMapping("selectListOrderByHouseId/{houseId}")
    public JsonResult selectListOrderByHouseId(@PathVariable("houseId") Integer houseId){
        List<Order> orders = hotelService.selectListOrderByHouseId(houseId);
        JsonResult result = new JsonResult<>(0, "", orders);
        return result;
    }

    @RequestMapping("selectByName/{hotelName}")
    public JsonResult selectByName(@PathVariable("hotelName") String hotelName){
        List<Hotel> hotels = hotelService.selectByName(hotelName);
        //设置页码和每页数量
        JsonResult result = new JsonResult<>(0, "", hotels);
        return result;
    }

    @RequestMapping("selectHotelById/{id}")
    public JsonResult selectHotelById(@PathVariable("id") Integer id){
        Hotel hotel = hotelService.selectHotelById(id);
        return new JsonResult<>(0, "", hotel);
    }

    @RequestMapping("selectHouseById/{id}")
    public JsonResult selectHouseById(@PathVariable("id") Integer id){
        House house = hotelService.selectHouseById(id);
        return new JsonResult<>(0, "", house);
    }


    @RequestMapping("selectImageById/{hotelId}")
    public JsonResult selectImageById(@PathVariable("hotelId") Integer hotelId) {
        List<HotelImage> hotelImages = hotelService.selectImgByHotelId(hotelId);
        JsonResult result = new JsonResult<>(0, "", hotelImages);
        return result;
    }

    @RequestMapping("selectImage")
    public JsonResult selectImage() {
        List<HotelImage> hotelImages = hotelService.selectImage();
        JsonResult result = new JsonResult<>(0, "", hotelImages);
        return result;
    }


    @RequestMapping("updateHotel")
    public JsonResult updateHotel(Hotel hotel, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = hotelService.updateHotel(hotel);
        if (b) {
            return new JsonResult<>(0, "更新成功", selectHotelById(hotel.getId()));
        } else {
            return new JsonResult<>(1,"更新失败",null);
        }
    }

    @RequestMapping("updateHouse")
    public JsonResult updateHouse(House house, HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = hotelService.updateHouse(house);
        if (b) {
            return new JsonResult<>(0, "更新成功", selectHouseById(house.getId()));
        } else {
            return new JsonResult<>(1,"更新失败",null);
        }
    }


    @RequestMapping("updateImage")
    public JsonResult updateImage(HotelImage hotelImage) {
        boolean b = hotelService.updateImage(hotelImage);
        if (b){
            return new JsonResult<>(0, "成功", true);
        }
        return new JsonResult<>(1, "失败", false);
    }

    @RequestMapping("insertImage")
    public JsonResult insertImage(HotelImage hotelImage,HttpSession session){
//        if (isAdminLogin(session)){
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        hotelService.insertImage(hotelImage);
        return new JsonResult<>(0,"",hotelImage);
    }

    @RequestMapping("deleteHotelById/{id}")
    public JsonResult deleteHotelById(@PathVariable("id") Integer id){
        boolean b = hotelService.deleteHotelById(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(0,"没有找到对象",false);
        }
    }

    @RequestMapping("deleteOrderById/{id}")
    public JsonResult deleteOrderById(@PathVariable("id") Integer id){
        boolean b = hotelService.deleteOrderById(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(0,"没有找到对象",false);
        }
    }


    @RequestMapping("deleteHouseById/{id}")
    public JsonResult deleteHouseById(@PathVariable("id") Integer id){
        boolean b = hotelService.deleteHouseById(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(0,"没有找到对象",false);
        }
    }

    @RequestMapping("insertHotel")
    public JsonResult insertHotel(Hotel hotel,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        Integer id = hotelService.insertHotel(hotel);
        if (id > 0) {
            return new JsonResult<>(0, "新增成功", hotel);
        } else {
            return new JsonResult<>(0,"新增失败",null);
        }
    }

    @RequestMapping("insertHouse")
    public JsonResult insertHouse(House house,HttpSession session){
        //        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        Integer id = hotelService.insertHouse(house);
        if (id > 0) {
            return new JsonResult<>(0, "新增成功", hotelService.selectHouseById(id));
        } else {
            return new JsonResult<>(0,"新增失败",null);
        }
    }

    @RequestMapping("countHouse/{hotelId}")
    public JsonResult countHouse(@PathVariable("hotelId") Integer hotelId){
        List<House> houses = hotelService.countHouse(hotelId);
        JsonResult result = new JsonResult<>(0, "", houses);
        return result;
    }

    @RequestMapping("insertOrder")
    public JsonResult insertOrder(Order order,HttpSession session){
                if (isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        order.setTime(new Date());
        int id = hotelService.insertOrder(order);

        if (id > 0) {
            return new JsonResult<>(0, "成功", order);
        } else {
            return new JsonResult<>(1,"失败",null);
        }
    }

    @RequestMapping("selectByUserId/{userId}")
    public JsonResult selectByUserId(@PathVariable("userId") Integer userId){
        List<Order> orders = hotelService.selectOrderByUserId(userId);
        //设置页码和每页数量
        JsonResult result = new JsonResult<>(0, "", orders);
        return result;
    }

    @RequestMapping("searchHotel/{hotelName}")
    public JsonResult searchHotel(@PathVariable("hotelName") String hotelName){
        System.out.println(hotelName);
        List<Hotel> hotels = hotelService.searchHotel(hotelName);
        //设置页码和每页数量
        JsonResult result = new JsonResult<>(0, "", hotels);
        return result;
    }


    @RequestMapping("updateOrder")
    public JsonResult updateOrder(Order order){
        boolean b = hotelService.updateOrder(order);
        if (b) {
            return new JsonResult<>(0, "更新成功", hotelService.selectOrderById(order.getId()));
        } else {
            return new JsonResult<>(1,"更新失败",null);
        }
    }
    @RequestMapping("deleteOrder/{id}")
    public JsonResult deleteOrder(@PathVariable("id") Integer id){
        boolean b = hotelService.deleteOrder(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(1,"删除失败",false);
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
