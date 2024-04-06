package com.ysu.service;

import com.github.pagehelper.PageInfo;
import com.ysu.entity.Attraction;
import com.ysu.entity.AttractionImage;
import com.ysu.entity.Comment;
import com.ysu.entity.Ticket;

import java.util.List;

public interface AttractionService {
    //显示所有景点
    List<Attraction> selectListAttraction();

    //查找景点(名称查找)
    List<Attraction> selectByName(String attName);

    //查找景点（id查找）
    Attraction selectById(Integer id);

    //删除景点
    boolean deleteAttractionById(Integer id);

    //新增景点评价
    int insertComment(Comment comment);

    //删除评价
    boolean deleteCommentById(Integer id);

    //查看评论
    List<Comment> selectComment(Integer attId);

    //景点剩余总票数
    List<Ticket> ticketCount(Integer id);

    void insert(Attraction attraction);

    boolean update(Attraction attraction);

    //查找景点图片
    List<AttractionImage> selectImgByAttId(Integer attId);

    boolean updateImage(AttractionImage attractionImage);


    void insertImage(AttractionImage attractionImage);

    List<AttractionImage> selectImage();
}
