package com.ysu.service.impl;

import com.ysu.dao.StrategyMapper;
import com.ysu.entity.Comment;
import com.ysu.entity.Strategy;
import com.ysu.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyServiceImpl implements StrategyService {
    @Autowired
    private StrategyMapper strategyMapper;

    @Override
    public Integer insertStrategy(Strategy strategy) {
        strategyMapper.insertStrategy(strategy);
        return strategy.getId();
    }

    @Override
    public List<Strategy> selectListStrategy() {
        return strategyMapper.selectListStrategy();
    }

    @Override
    public List<Strategy> selectByTitle(String title) {
        return strategyMapper.selectByTitle(title);
    }

    @Override
    public List<Strategy> selectByType(String type) {
        return strategyMapper.selectByType(type);
    }

    @Override
    public boolean updateStrategy(Strategy strategy) {
        int rows = strategyMapper.updateStrategy(strategy);
        return rows > 0;
    }

    @Override
    public boolean deleteStrategyById(Integer id) {
        int rows = strategyMapper.deleteStrategyById(id);
        return rows > 0;
    }

    @Override
    public Strategy selectStrategyById(Integer id) {
        return strategyMapper.selectStrategyById(id);
    }

    @Override
    public Integer insertComment(Comment comment) {
        strategyMapper.insertComment(comment);
        return comment.getId();
    }

    @Override
    public boolean deleteComment(Integer id) {
        int rows = strategyMapper.deleteCommentById(id);
        return rows > 0;
    }

    @Override
    public List<Comment> selectListCommentById(Integer strId) {
        return strategyMapper.selectListCommentById(strId);
    }

    @Override
    public List<Strategy> selectStrategyTop() {
        return strategyMapper.selectStrategyTop();
    }

}
