package com.ysu.service;

import com.ysu.entity.Bus;

import java.util.List;

public interface BusService {
    //根据线路名称查询
    List<Bus> selectByBusName(String busName);
    //站点查询
    List<Bus> selectListByStation(String pathway);

    Integer insert(Bus bus);

    boolean deleteById(Integer id);

    boolean update(Bus bus);

    List<Bus> selectList();

    Bus selectById(Integer id);
}
