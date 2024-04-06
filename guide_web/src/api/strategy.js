import request from '@/utils/request'
export function getStrategy(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/strategy/selectListStrategy`,
        method: 'post',
        data
    })
}

export function getStrategyById(data) {
    return request({
        url: `/api/strategy/selectStrategyById/${data.id}`,
        method: 'post',
        data
    })
}

export function getStrategyByType(data) {
    return request({
        url: `/api/strategy/selectByType/${data.type}`,
        method: 'post',
        data
    })
}

export function getStrategyTop(data) {
    return request({
        url: `/api/strategy/selectStrategyTop`,
        method: 'post',
        data
    })
}

export function updateStrategy(data) {
    return request({
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: '/api/strategy/updateStrategy',
        method: 'post',
        data
    })
}

export function addStrategy(data) {
    return request({
        url: '/api/strategy/insertStrategy',
        //post请求
        method: 'post',
        data
    })
}

export function deleteStratety(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/strategy/deleteStrategyById/${data.id}`,
        method: 'post',
        data
    })
}

export function getComment(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/strategy/selectListCommentById/${data.id}`,
        method: 'post',
        data
    })
}
export function deleteComment(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/strategy/deleteCommentById/${data.id}`,
        method: 'post',
        data
    })
}

export function addComment(data) {
    return request({
        url: '/api/strategy/insertComment',
        //post请求
        method: 'post',
        data
    })
}