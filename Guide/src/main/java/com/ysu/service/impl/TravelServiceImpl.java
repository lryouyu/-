package com.ysu.service.impl;

import com.ysu.dao.TravelMapper;
import com.ysu.entity.Comment;
import com.ysu.entity.Travel;
import com.ysu.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TravelServiceImpl implements TravelService {
    @Autowired
    private TravelMapper travelMapper;

    @Override
    public Integer insertTravel(Travel travel) {
        travelMapper.insertTravel(travel);
        return travel.getId();
    }

    @Override
    public List<Travel> selectListTravel() {
        return travelMapper.selectListTravel();
    }

    @Override
    public List<Travel> selectByTitle(String title) {
        return travelMapper.selectByTitle(title);
    }

    @Override
    public List<Travel> selectByType(String type) {
        return travelMapper.selectByType(type);
    }

    @Override
    public Travel selectTravelById(Integer id) {
        return travelMapper.selectTravelById(id);
    }

    @Override
    public boolean deleteTravelById(Integer id) {
        int rows = travelMapper.deleteTravelById(id);
        return rows > 0;
    }

    @Override
    public boolean updateTravel(Travel travel) {
        int rows = travelMapper.updateTravel(travel);

        return rows > 0;
    }

    @Override
    public List<Travel> selectTravelByUserId(Integer userId) {
        return travelMapper.selectTravelUserById(userId);
    }


}
