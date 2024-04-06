package com.ysu.entity;

import lombok.Data;

@Data
public class FoodImage {
    private String imgPath;
    private Integer id;
    private Integer foodId;
}
