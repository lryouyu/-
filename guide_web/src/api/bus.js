import request from '@/utils/request'
export function getBuses(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/bus/selectList`,
        method: 'post',
        data
    })
}

export function addBus(data) {
    return request({
        url: '/api/bus/insert',
        //post请求
        method: 'post',
        data
    })
}

export function getBusById(data) {
    return request({
        url: `/api/bus/selectById/${data.id}`,
        method: 'post',
        data
    })
}

export function deleteBus(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/bus/delete/${data.id}`,
        method: 'post',
        data
    })
}

export function updateBus(data) {
    return request({
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: '/api/bus/update',
        method: 'post',
        data
    })
}

export function getBusByName(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/bus/selectByName/${data.busName}`,
        method: 'post',
        data
    })
}

export function getBusByStation(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/bus/selectByStation/${data.pathway}`,
        method: 'post',
        data
    })
}




