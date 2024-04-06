import request from '@/utils/request'
export function getTravels(data) {
    return request({
        url: `/api/travel/selectListTravel`,
        method: 'post',
        data
    })
}



export function getTravelById(data) {
    return request({
        url: `/api/travel/selectTravelById/${data.id}`,
        method: 'post',
        data
    })
}

export function getTravelByUserId(data) {
    return request({
        url: `/api/travel/selectTravelByUserId/${data.userId}`,
        method: 'post',
        data
    })
}
export function deleteTravel(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/travel/deleteTravelById/${data.id}`,
        method: 'post',
        data
    })
}

export function getTravelByType(data) {
    return request({
        url: `/api/travel/selectByType/${data.type}`,
        method: 'post',
        data
    })
}
export function updateTravel(data) {
    return request({
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: '/api/travel/updateTravel',
        method: 'post',
        data
    })
}

export function addTravel(data) {
    return request({
        url: '/api/travel/insertTravel',
        //post请求
        method: 'post',
        data
    })
}
export function getTravelByTitle(data) {
    return request({
        url: `/api/travel/selectByTitle/${data.title}`,
        method: 'post',
        data
    })
}

