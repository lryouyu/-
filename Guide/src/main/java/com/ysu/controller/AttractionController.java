package com.ysu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.common.JsonResult;
import com.ysu.entity.*;
import com.ysu.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("attraction")
public class AttractionController {
    @Autowired
    private AttractionService attractionService;

    @RequestMapping("all")
    public JsonResult selectList() {
        List<Attraction> attractions = attractionService.selectListAttraction();
        JsonResult result = new JsonResult<>(0, "", attractions);
        return result;
    }

    @RequestMapping("selectByName/{attName}")
    public JsonResult selectByName(@PathVariable("attName") String attName) {
        List<Attraction> attractions = attractionService.selectByName(attName);
        JsonResult result = new JsonResult<>(0, "", attractions);
        return result;
    }

    @RequestMapping("selectById/{id}")
    public JsonResult selectById(@PathVariable("id") Integer id,HttpSession session) {
        if (isAdminLogin(session)&&isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        Attraction attraction = attractionService.selectById(id);
        JsonResult result = new JsonResult<>(0, "", attraction);
        return result;
    }

    @RequestMapping("selectCommentById/{attId}")
    public JsonResult selectCommentById(@PathVariable("attId") Integer attId,HttpSession session) {
        if (isAdminLogin(session)&&isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        List<Comment> comments = attractionService.selectComment(attId);
        JsonResult result = new JsonResult<>(0, "", comments);
        return result;
    }

    @RequestMapping("selectImageById/{attId}")
    public JsonResult selectImageById(@PathVariable("attId") Integer attId) {
        List<AttractionImage> attractionImages = attractionService.selectImgByAttId(attId);
        JsonResult result = new JsonResult<>(0, "", attractionImages);
        return result;
    }

    @RequestMapping("updateImage")
    public JsonResult updateImage(AttractionImage attractionImage,HttpSession session) {
        if (isAdminLogin(session)&&isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        boolean b = attractionService.updateImage(attractionImage);
        if (b){
            return new JsonResult<>(0, "成功", true);
        }
        return new JsonResult<>(1, "失败", false);
    }


    @RequestMapping("insertComment")
    public JsonResult insertComment(Comment comment, HttpSession session) {
        if (isAdminLogin(session)&&isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        comment.setTime(String.valueOf(new Date()));
        Integer id = attractionService.insertComment(comment);
        JsonResult result = new JsonResult<>(0, "", comment);
        return result;
    }

    @RequestMapping("insert")
    public JsonResult insert(Attraction attraction,HttpSession session){
        if (isAdminLogin(session)&&isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        attractionService.insert(attraction);
        return new JsonResult<>(0,"",attraction);
    }
    @RequestMapping("insertImage")
    public JsonResult insertImage(AttractionImage attractionImage,HttpSession session){
        if (isAdminLogin(session)&&isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        attractionService.insertImage(attractionImage);
        return new JsonResult<>(0,"",attractionImage);
    }

    @RequestMapping("update")
    public JsonResult update(Attraction attraction, HttpSession session){
        if (isAdminLogin(session)&&isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        boolean bl = attractionService.update(attraction);
        if (bl){
            return new JsonResult<>(0, "成功", true);
        }
        return new JsonResult<>(1, "失败", false);
    }

    @RequestMapping("deleteById/{id}")
    public JsonResult deleteAttraction(@PathVariable("id") Integer id,HttpSession session){
        if (isAdminLogin(session)&&isUserLogin(session)) {
            return new JsonResult<>(-1, "未登录或登陆过期，请重新登录", null);
        }
        boolean b = attractionService.deleteAttractionById(id);
        if (b){
            return new JsonResult<>(0, "成功", true);
        }
        return new JsonResult<>(1, "失败", false);
    }

    @RequestMapping("selectImage")
    public JsonResult selectImage() {
        List<AttractionImage> attractionImages = attractionService.selectImage();
        JsonResult result = new JsonResult<>(0, "", attractionImages);
        return result;
    }



    @RequestMapping("deleteCommentById/{id}")
    public JsonResult deleteCommentById(@PathVariable("id") Integer id){
        boolean b = attractionService.deleteCommentById(id);
        if (b){
            return new JsonResult<>(0, "成功", true);
        }
        return new JsonResult<>(1, "失败", false);
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
