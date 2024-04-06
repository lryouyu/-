package com.ysu.dao;

import com.ysu.entity.Hotel;
import com.ysu.entity.HotelImage;
import com.ysu.entity.House;
import com.ysu.entity.Order;

import java.util.List;

public interface HotelMapper {
    List<Hotel> selectListHotel();

    List<Hotel> selectByName(String hotelName);

    Hotel selectHotelById(Integer id);

    int updateHotel(Hotel hotel);

    int deleteHotelById(Integer id);

    void insertHotel(Hotel hotel);

    List<House> countHouse(Integer hotelId);


    void insertOrder(Order order);

    List<Order> selectByUserId(Integer userId);

    int updateOrder(Order order);

    int deleteOrder(Integer id);

    Order selectOrderById(Integer id);

    List<HotelImage> selectImgByHotelId(Integer hotelId);

    int updateImage(HotelImage hotelImage);

    void insertImage(HotelImage hotelImage);

    int deleteHouseById(Integer id);

    House selectHouseById(Integer id);

    int updateHouse(House house);

    void insertHouse(House house);

    List<Order> selectListOrder();

    List<HotelImage> selectImage();

    List<Hotel> searchHotel(String hotelName);

    List<Order> selectListOrderByUserId(Integer userId);

    List<Order> selectListOrderByHouseId(Integer houseId);
}
