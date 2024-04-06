package com.ysu.service.impl;

import com.ysu.dao.FoodMapper;
import com.ysu.entity.Food;
import com.ysu.entity.FoodImage;
import com.ysu.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodMapper foodMapper;
    @Override
    public int insertFood(Food food) {
        foodMapper.insert(food);
        return food.getId();
    }

    @Override
    public boolean deleteById(Integer id) {
        int rows = foodMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public List<Food> selectListByAtt(Integer attId) {
        List<Food> foods = foodMapper.selectListByAtt(attId);
        return foods;
    }

    @Override
    public boolean update(Food food) {
        int rows = foodMapper.update(food);
        return rows > 0;
    }

    @Override
    public List<Food> selectByName(String foodName) {
        List<Food> foods = foodMapper.selectByName(foodName);
        return foods;
    }

    @Override
    public Food selectById(Integer id) {
        Food food = foodMapper.selectById(id);
        return food;
    }

    @Override
    public int insertFoodImage(FoodImage foodImage) {
        foodMapper.insertImage(foodImage);
        return foodImage.getId();
    }

    @Override
    public List<Food> selectList() {
        return foodMapper.selectList();
    }

    @Override
    public List<FoodImage> selectImageById(Integer foodId) {
        return foodMapper.selectImageById(foodId);
    }

    @Override
    public boolean updateImage(FoodImage foodImage) {
        int rows = foodMapper.updateImage(foodImage);
        return rows > 0;
    }

    @Override
    public List<Food> selectByAttName(String attName) {
        return foodMapper.selectByAttName(attName);
    }
}
