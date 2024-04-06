import request from '@/utils/request'
export function getFood(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/food/selectById/${data.id}`,
        method: 'post',
        data
    })
}
export function updateFood(data) {
    return request({
        url: '/api/food/update',
        method: 'post',
        data
    })
}

export function getImageUrl(data) {
    return request({
        //设置请求数据格式为json
        // headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/food/selectImageById/${data.id}`,
        method: 'post',
        data
    })
}

export function updateImage(data) {
    return request({
        url: '/api/food/updateImage',
        //post请求
        method: 'post',
        data
    })
}


export function getFoods(data) {
    return request({
        //设置请求数据格式为json
        url: '/api/food/selectList',
        method: 'post',
        data
    })
}
export function getFoodsByName(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/food/selectByName/${data.foodName}`,
        method: 'post',
        data
    })
}

export function getFoodsByAttName(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/food/selectByAttName/${data.attName}`,
        method: 'post',
        data
    })
}
export function addFood(data) {
    return request({
        url: '/api/food/insert',
        //post请求
        method: 'post',
        data
    })
}
export function addImage(data) {
    return request({
        url: '/api/food/insertImage',
        //post请求
        method: 'post',
        data
    })
}

export function deleteFood(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/food/deleteById/${data.id}`,
        method: 'post',
        data
    })
}
