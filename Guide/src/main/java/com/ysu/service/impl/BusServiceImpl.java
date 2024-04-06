package com.ysu.service.impl;

import com.ysu.dao.BusMapper;
import com.ysu.entity.Bus;
import com.ysu.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusMapper busMapper;
    @Override
    public List<Bus> selectByBusName(String busName) {
        List<Bus> busList = busMapper.selectByName(busName);
        return busList;
    }

    @Override
    public List<Bus> selectListByStation(String pathway) {
        List<Bus> busList = busMapper.selectByStation(pathway);
        return busList;
    }


    @Override
    public Integer insert(Bus bus) {
        busMapper.insert(bus);
        return bus.getId();
    }

    @Override
    public boolean deleteById(Integer id) {
        int rows = busMapper.deleteById(id);
        return rows > 0;
    }

    @Override
    public boolean update(Bus bus) {
        int rows = busMapper.update(bus);
        return rows > 0;
    }

    @Override
    public List<Bus> selectList() {
        return busMapper.selectList();

    }

    @Override
    public Bus selectById(Integer id) {
        return busMapper.selectById(id);
    }
}
