package com.ysu.service;

import com.ysu.entity.Food;
import com.ysu.entity.FoodImage;

import java.util.List;

public interface FoodService {
    int insertFood(Food food);
    boolean deleteById(Integer id);
    List<Food> selectListByAtt(Integer attId);

    boolean update(Food food);

    List<Food> selectByName(String foodName);

    Food selectById(Integer id);

    int insertFoodImage(FoodImage foodImage);

    List<Food> selectList();

    List<FoodImage> selectImageById(Integer FoodId);

    boolean updateImage(FoodImage foodImage);

    List<Food> selectByAttName(String attName);
}
