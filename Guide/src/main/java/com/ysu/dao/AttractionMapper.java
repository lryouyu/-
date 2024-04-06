package com.ysu.dao;

import com.ysu.entity.Attraction;
import com.ysu.entity.AttractionImage;
import com.ysu.entity.Comment;
import com.ysu.entity.Ticket;

import java.util.List;

public interface AttractionMapper {
    List<Attraction> selectList();

    List<Attraction> selectByName(String attName);

    Attraction selectById(Integer id);

    int deleteAttractionById(Integer id);

    int deleteCommentById(Integer id);

    void insertComment(Comment comment);

    List<Ticket> ticketCount(Integer attId);

    List<Comment> selectComment(Integer id);

    void insert(Attraction attraction);

    int update(Attraction attraction);

    List<AttractionImage> selectImgByAttId(Integer attId);

    int updateImage(AttractionImage attractionImage);

    void insertImage(AttractionImage attractionImage);

    List<AttractionImage> selectImage();
}
