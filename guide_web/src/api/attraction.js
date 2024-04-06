import request from '@/utils/request'
export function getAttraction(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/attraction/selectById/${data.id}`,
        method: 'post',
        data
    })
}
export function updateAttraction(data) {
    return request({
        url: '/api/attraction/update',
        method: 'post',
        data
    })
}

export function getComment(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/attraction/selectCommentById/${data.id}`,
        method: 'post',
        data
    })
}

export function getImageUrl(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/attraction/selectImageById/${data.id}`,
        method: 'post',
        data
    })
}

export function getImageUrls(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/attraction/selectImage`,
        method: 'post',
        data
    })
}

export function updateImage(data) {
    return request({
        url: '/api/attraction/updateImage',
        //post请求
        method: 'post',
        data
    })
}


export function getAttractions(data) {
    return request({
        //设置请求数据格式为json
        url: '/api/attraction/all',
        method: 'post',
        data
    })
}
export function addAttraction(data) {
    return request({
        url: '/api/attraction/insert',
        //post请求
        method: 'post',
        data
    })
}
export function addImage(data) {
    return request({
        url: '/api/attraction/insertImage',
        //post请求
        method: 'post',
        data
    })
}

export function deleteAttraction(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/attraction/deleteById/${data.id}`,
        method: 'post',
        data
    })
}
export function deleteComment(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/attraction/deleteCommentById/${data.id}`,
        method: 'post',
        data
    })
}


export function searchAttraction(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/attraction/selectByName/${data.attName}`,
        method: 'post',
        data
    })
}
export function addComment(data) {
    return request({
        url: '/api/attraction/insertComment',
        //post请求
        method: 'post',
        data
    })
}