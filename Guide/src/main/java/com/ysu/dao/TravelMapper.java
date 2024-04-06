package com.ysu.dao;

import com.ysu.entity.Comment;
import com.ysu.entity.Strategy;
import com.ysu.entity.Travel;

import java.util.List;

public interface TravelMapper {
    void insertTravel(Travel travel);

    List<Travel> selectListTravel();

    List<Travel> selectByTitle(String title);

    List<Travel> selectByType(String type);

    Travel selectTravelById(Integer id);

    int deleteTravelById(Integer id);


    int updateTravel(Travel travel);

    List<Travel> selectTravelUserById(Integer userId);

}
