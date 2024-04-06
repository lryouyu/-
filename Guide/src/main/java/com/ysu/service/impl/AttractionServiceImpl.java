package com.ysu.service.impl;

import com.ysu.dao.AttractionMapper;
import com.ysu.entity.Attraction;
import com.ysu.entity.AttractionImage;
import com.ysu.entity.Comment;
import com.ysu.entity.Ticket;
import com.ysu.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {
    @Autowired
    private AttractionMapper attractionMapper;

    @Override
    public List<Attraction> selectListAttraction() {
        List<Attraction> attractions = attractionMapper.selectList();
        return attractions;
    }

    @Override
    public List<Attraction> selectByName(String attName) {
        List<Attraction> attractions = attractionMapper.selectByName(attName);
        return attractions;
    }


    @Override
    public Attraction selectById(Integer id) {
        Attraction attraction = attractionMapper.selectById(id);
        return attraction;
    }

    @Override
    public boolean deleteAttractionById(Integer id) {
        int rows = attractionMapper.deleteAttractionById(id);
        return rows > 0;
    }

    @Override
    public int insertComment(Comment comment) {
        attractionMapper.insertComment(comment);
        return comment.getId();
    }

    @Override
    public boolean deleteCommentById(Integer id) {
        int rows = attractionMapper.deleteCommentById(id);
        return rows > 0;
    }

    @Override
    public List<Comment> selectComment(Integer attId) {
        List<Comment> comments=attractionMapper.selectComment(attId);
        return comments;
    }

    @Override
    public List<Ticket> ticketCount(Integer attId) {
        List<Ticket> tickets=attractionMapper.ticketCount(attId);
        return tickets;
    }

    @Override
    public void insert(Attraction attraction) {
        attractionMapper.insert(attraction);
    }

    @Override
    public boolean update(Attraction attraction) {
        int rows = attractionMapper.update(attraction);
        return rows > 0;
    }

    @Override
    public List<AttractionImage> selectImgByAttId(Integer attId) {
        List<AttractionImage> attractionImages = attractionMapper.selectImgByAttId(attId);
        return attractionImages;
    }

    @Override
    public boolean updateImage(AttractionImage attractionImage) {
        int rows = attractionMapper.updateImage(attractionImage);
        return rows > 0;
    }

    @Override
    public void insertImage(AttractionImage attractionImage) {
        attractionMapper.insertImage(attractionImage);
    }

    @Override
    public List<AttractionImage> selectImage() {
        return attractionMapper.selectImage();
    }


}
