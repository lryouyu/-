package com.ysu.service;

import com.ysu.entity.Travel;

import java.util.List;

public interface TravelService {

    Integer insertTravel(Travel travel);
    //显示所有游记
    List<Travel> selectListTravel();
    //查找游记(名称查找)
    List<Travel> selectByTitle(String title);
    //根据分类
    List<Travel> selectByType(String type);

    //查找景点（id查找）
    Travel selectTravelById(Integer id);

    boolean deleteTravelById(Integer id);


    boolean updateTravel(Travel travel);

    List<Travel> selectTravelByUserId(Integer userId);
}
