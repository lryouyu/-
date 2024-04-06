import request from '@/utils/request'

export function userlogin(data) {
    return request({
        url: '/api/user/login',
        //post请求
        method: 'post',
        data
    })
}

export function adminlogin(data) {
    return request({
        url: '/api/admin/login',
        //post请求
        method: 'post',
        data
    })
}

export function addUser(data) {
    return request({
        url: '/api/user/insert',
        //post请求
        method: 'post',
        data
    })
}

export function userLogOut(params) {
    return request({
        url: '/api/user/logout',
        //get请求
        method: 'get',
        params
    })
}

export function adminlogout(params) {
    return request({
        url: '/api/admin/logout',
        //get请求
        method: 'get',
        params
    })
}

export function getUsers(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: '/api/user/all',
        method: 'post',
        data
    })
}

//上传图片
export function uploadImage(data) {
    return request({
        //设置请求数据格式为multipart/form-data
        headers: {"Content-Type": "multipart/form-data;charset=UTF-8"},
        url: '/api/user/image/upload',
        method: 'post',
        data
    })
}

export function getUser(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/user/selectById/${data.id}`,
        method: 'post',
        data
    })
}

export function updateUser(data) {
    return request({
        url: '/api/user/update',
        //post请求
        method: 'post',
        data
    })
}

export function deleteUser(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/user/delete/${data.id}`,
        method: 'post',
        data
    })
}

export function page(data) {
    return request({
        url: '/api/user/page',
        method: 'post',
        data
    })
}

export function checkIsUser(data) {
    return request({
        url: '/api/user/check',
        //post请求
        method: 'post',
        data
    })
}