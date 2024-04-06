import request from '@/utils/request'
export function getHotel(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/hotel/selectHotelById/${data.id}`,
        method: 'post',
        data
    })
}
export function updateHotel(data) {
    return request({
        url: '/api/hotel/updateHotel',
        method: 'post',
        data
    })
}

export function updateHouse(data) {
    return request({
        url: '/api/hotel/updateHouse',
        method: 'post',
        data
    })
}

export function getImageUrl(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/hotel/selectImageById/${data.id}`,
        method: 'post',
        data
    })
}

export function updateImage(data) {
    return request({
        url: '/api/hotel/updateImage',
        //post请求
        method: 'post',
        data
    })
}


export function getHotels(data) {
    return request({
        //设置请求数据格式为json
        url: '/api/hotel/selectList',
        method: 'post',
        data
    })
}
export function addHotel(data) {
    return request({
        url: '/api/hotel/insertHotel',
        //post请求
        method: 'post',
        data
    })
}

export function addHouse(data) {
    return request({
        url: '/api/hotel/insertHouse',
        //post请求
        method: 'post',
        data
    })
}
export function addOrder(data) {
    return request({
        url: '/api/hotel/insertOrder',
        //post请求
        method: 'post',
        data
    })
}
export function addImage(data) {
    return request({
        url: '/api/hotel/insertImage',
        //post请求
        method: 'post',
        data
    })
}

export function deleteHotel(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/hotel/deleteHotelById/${data.id}`,
        method: 'post',
        data
    })
}
export function deleteOrder(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/hotel/deleteOrderById/${data.id}`,
        method: 'post',
        data
    })
}

export function getHouses(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/hotel/countHouse/${data.id}`,
        method: 'post',
        data
    })
}

export function getOrders(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/hotel/selectListOrder`,
        method: 'post',
        data
    })
}

export function getOrdersByUserId(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/hotel/selectListOrderByUserId/${data.id}`,
        method: 'post',
        data
    })
}

export function getOrdersByHouseId(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/hotel/selectListOrderByHouseId/${data.houseId}`,
        method: 'post',
        data
    })
}

export function getHouse(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/hotel/selectHouseById/${data.id}`,
        method: 'post',
        data
    })
}


export function deleteHouse(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/hotel/deleteHouseById/${data.id}`,
        method: 'post',
        data
    })
}

export function getImageUrls(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/hotel/selectImage`,
        method: 'post',
        data
    })
}

export function searchHotel(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/hotel/searchHotel/${data.hotelName}`,
        method: 'post',
        data
    })
}