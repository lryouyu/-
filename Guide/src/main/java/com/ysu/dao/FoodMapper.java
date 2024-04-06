package com.ysu.dao;

import com.ysu.entity.Food;
import com.ysu.entity.FoodImage;

import java.util.List;

public interface FoodMapper {
    void insert(Food food);

    int deleteById(Integer id);

    List<Food> selectListByAtt(Integer attId);

    int update(Food food);

    List<Food> selectByName(String foodName);

    Food selectById(Integer id);

    void insertImage(FoodImage foodImage);

    List<Food> selectList();

    List<FoodImage> selectImageById(Integer foodId);

    int updateImage(FoodImage foodImage);

    List<Food> selectByAttName(String attName);
}
