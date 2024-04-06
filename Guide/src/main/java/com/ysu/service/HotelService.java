package com.ysu.service;

import com.ysu.entity.*;

import java.util.List;

public interface HotelService {
    List<Hotel> selectListHotel();

    List<Hotel> selectByName(String hotelName);

    Hotel selectHotelById(Integer id);

    boolean updateHotel(Hotel hotel);

    boolean deleteHotelById(Integer id);

    Integer insertHotel(Hotel hotel);

    //查房型
    List<House> countHouse(Integer hotelId);

    //入住
    Integer insertOrder(Order order);

    //用户查询订单
    List<Order> selectOrderByUserId(Integer userId);

    //修改日期
    boolean updateOrder(Order order);

    //退房
    boolean deleteOrder(Integer id);

    Order selectOrderById(Integer id);

    List<HotelImage> selectImgByHotelId(Integer hotelId);

    boolean updateImage(HotelImage hotelImage);

    void insertImage(HotelImage hotelImage);

    boolean deleteHouseById(Integer id);

    House selectHouseById(Integer id);

    boolean updateHouse(House house);

    Integer insertHouse(House house);

    List<Order> selectListOrder();

    boolean deleteOrderById(Integer id);

    List<HotelImage> selectImage();

    List<Hotel> searchHotel(String hotelName);

    List<Order> selectListOrderByUserId(Integer userId);

    List<Order> selectListOrderByHouseId(Integer houseId);
}
