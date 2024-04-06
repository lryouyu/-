package com.ysu.dao;

import com.ysu.entity.Bus;

import java.util.List;

public interface BusMapper {

    List<Bus> selectByName(String busName);

    List<Bus> selectByStation(String pathway);

    void insert(Bus bus);

    int deleteById(Integer id);

    int update(Bus bus);

    List<Bus> selectList();

    Bus selectById(Integer id);
}
