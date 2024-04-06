package com.ysu.service.impl;

import com.ysu.dao.HotelMapper;
import com.ysu.entity.*;
import com.ysu.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;
    @Override
    public List<Hotel> selectListHotel() {
        List<Hotel> hotels = hotelMapper.selectListHotel();
        return hotels;
    }

    @Override
    public List<Hotel> selectByName(String hotelName) {
        List<Hotel> hotels = hotelMapper.selectByName(hotelName);
        return hotels;
    }

    @Override
    public Hotel selectHotelById(Integer  id) {
        return hotelMapper.selectHotelById(id);
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        int rows = hotelMapper.updateHotel(hotel);
        return rows > 0;
    }

    @Override
    public boolean deleteHotelById(Integer id) {
        int rows = hotelMapper.deleteHotelById(id);
        return rows > 0;
    }

    @Override
    public Integer insertHotel(Hotel hotel) {
        hotelMapper.insertHotel(hotel);
        return hotel.getId();
    }

    //查询房型
    @Override
    public List<House> countHouse(Integer hotelId) {
        List<House> houses = hotelMapper.countHouse(hotelId);
        return houses;
    }


    @Override
    public Integer insertOrder(Order order) {
        hotelMapper.insertOrder(order);
        return order.getId();
    }

    @Override
    public List<Order> selectOrderByUserId(Integer userId) {
        List<Order> orders = hotelMapper.selectByUserId(userId);
        return orders;
    }

    @Override
    public boolean updateOrder(Order order) {
        int rows = hotelMapper.updateOrder(order);
        return rows > 0;
    }

    @Override
    public boolean deleteOrder(Integer id) {
        int rows = hotelMapper.deleteOrder(id);
        return rows > 0;
    }

    @Override
    public Order selectOrderById(Integer id) {
        return hotelMapper.selectOrderById(id);
    }

    @Override
    public List<HotelImage> selectImgByHotelId(Integer hotelId) {
        return hotelMapper.selectImgByHotelId(hotelId);
    }

    @Override
    public boolean updateImage(HotelImage hotelImage) {
        int rows = hotelMapper.updateImage(hotelImage);
        return rows > 0;
    }

    @Override
    public void insertImage(HotelImage hotelImage) {
        hotelMapper.insertImage(hotelImage);
    }

    @Override
    public boolean deleteHouseById(Integer id) {
        int rows = hotelMapper.deleteHouseById(id);
        return rows > 0;
    }

    @Override
    public House selectHouseById(Integer id) {
        return hotelMapper.selectHouseById(id);
    }

    @Override
    public boolean updateHouse(House house) {
        int rows = hotelMapper.updateHouse(house);
        return rows > 0;
    }

    @Override
    public Integer insertHouse(House house) {
        hotelMapper.insertHouse(house);
        return house.getId();
    }

    @Override
    public List<Order> selectListOrder() {
        return hotelMapper.selectListOrder();
    }

    @Override
    public boolean deleteOrderById(Integer id) {
        int rows = hotelMapper.deleteOrder(id);
        return rows > 0;
    }

    @Override
    public List<HotelImage> selectImage() {
        return hotelMapper.selectImage();
    }

    @Override
    public List<Hotel> searchHotel(String hotelName) {
        return hotelMapper.searchHotel(hotelName);
    }

    @Override
    public List<Order> selectListOrderByUserId(Integer userId) {
        return hotelMapper.selectListOrderByUserId(userId);
    }

    @Override
    public List<Order> selectListOrderByHouseId(Integer houseId) {
        return hotelMapper.selectListOrderByHouseId(houseId);
    }

}
