package com.ysu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.entity.Food;
import com.ysu.entity.FoodImage;
import com.ysu.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @RequestMapping("insert")
    public JsonResult insert(Food food,HttpSession session){
                if (isAdminLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        int id = foodService.insertFood(food);
        if (id > 0) {
            return new JsonResult<>(0, "新增成功", foodService.selectById(id));
        } else {
            return new JsonResult<>(0, "新增失败", null);
        }

    }


    @RequestMapping("insertImage")
    public JsonResult insertImage(FoodImage foodImage, HttpSession session){
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        int id = foodService.insertFoodImage(foodImage);
        if (id > 0) {
            return new JsonResult<>(0, "新增成功", foodService.selectById(id));
        } else {
            return new JsonResult<>(0, "新增失败", null);
        }

    }

    @RequestMapping("deleteById/{id}")
    public JsonResult deleteById(@PathVariable("id") Integer id,HttpSession session){
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = foodService.deleteById(id);
        if (b) {
            return new JsonResult<>(0, "删除成功", true);
        } else {
            return new JsonResult<>(0, "删除失败", false);
        }
    }

    @RequestMapping("update")
    public JsonResult update(Food food,HttpSession session){
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = foodService.update(food);
        if (b) {
            return new JsonResult<>(0, "更新成功", foodService.selectById(food.getId()));
        } else {
            return new JsonResult<>(1,"更新失败",null);
        }
    }

    @RequestMapping("updateImage")
    public JsonResult updateImage(FoodImage foodImage,HttpSession session){
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        boolean b = foodService.updateImage(foodImage);
        if (b) {
            return new JsonResult<>(0, "更新成功", true);
        } else {
            return new JsonResult<>(1,"更新失败",false);
        }
    }

    @RequestMapping("selectByName/{foodName}")
    public JsonResult selectByName(@PathVariable("foodName") String foodName){
        List<Food> foods = foodService.selectByName(foodName);
        if (foods.size() > 0) {
            return new JsonResult<>(0, "查询成功", foods);
        } else {
            return new JsonResult<>(0,"未查询到",null);
        }
    }

    @RequestMapping("selectByAttName/{attName}")
    public JsonResult selectByAttName(@PathVariable("attName") String attName){
        List<Food> foods = foodService.selectByAttName(attName);
        if (foods.size() > 0) {
            return new JsonResult<>(0, "查询成功", foods);
        } else {
            return new JsonResult<>(0,"未查询到",null);
        }
    }

    @RequestMapping("selectById/{id}")
    public JsonResult selectById(@PathVariable("id") Integer id,HttpSession session){
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        Food food = foodService.selectById(id);
        return new JsonResult(0,"",food);
    }

    @RequestMapping("selectList")
    public JsonResult selectList(){
        List<Food> food = foodService.selectList();
        return new JsonResult(0,"",food);
    }

    @RequestMapping("selectImageById/{id}")
    public JsonResult selectImageById(@PathVariable("id") Integer id,HttpSession session){
//        if (isAdminLogin(session)) {
//            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
//        }
        List<FoodImage> foodImages = foodService.selectImageById(id);
        return new JsonResult(0,"",foodImages);
    }


    boolean isAdminLogin(HttpSession session){
        if (session == null || session.getAttribute("admin") == null) {
            return true;
        }
        return false;
    }
}
