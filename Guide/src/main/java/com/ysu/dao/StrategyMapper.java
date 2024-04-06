package com.ysu.dao;

import com.ysu.entity.Comment;
import com.ysu.entity.Strategy;
import org.apache.ibatis.javassist.bytecode.StackMapTable;

import java.util.List;

public interface StrategyMapper {

    void insertStrategy(Strategy strategy);
    List<Strategy> selectListStrategy();

    List<Strategy> selectByTitle(String title);
    List<Strategy> selectByType(String type);

    int updateStrategy(Strategy strategy);

    Strategy selectStrategyById(Integer id);

    int deleteStrategyById(Integer id);

    void insertComment(Comment comment);

    int deleteCommentById(Integer id);


    List<Comment> selectListCommentById(Integer strId);

    List<Strategy> selectStrategyTop();
}
