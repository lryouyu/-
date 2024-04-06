package com.ysu.service;

import com.ysu.entity.Comment;
import com.ysu.entity.Strategy;

import java.util.List;

public interface StrategyService {

    Integer insertStrategy(Strategy strategy);
    //显示所有攻略
    List<Strategy> selectListStrategy();
    //查找攻略(名称查找)
    List<Strategy> selectByTitle(String title);
    //根据分类
    List<Strategy> selectByType(String type);

    boolean updateStrategy(Strategy strategy);

    boolean deleteStrategyById(Integer id);

    //查找景点（id查找）
    Strategy selectStrategyById(Integer id);

    //新增景点评价
    Integer insertComment(Comment comment);

    boolean deleteComment(Integer id);


    List<Comment> selectListCommentById(Integer strId);

    List<Strategy> selectStrategyTop();
}
