import request from '@/utils/request'
export function getTicket(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/ticket/selectTicketById/${data.id}`,
        method: 'post',
        data
    })
}
export function updateTicket(data) {
    return request({
        url: '/api/ticket/updateTicket',
        method: 'post',
        data
    })
}

export function getTickets(data) {
    return request({
        //设置请求数据格式为json
        url: '/api/ticket/selectListTicket',
        method: 'post',
        data
    })
}
export function getTicketsByAttId(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/ticket/selectTicketsByAttId/${data.id}`,
        method: 'post',
        data
    })
}
export function getUseTicketsByUserId(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/ticket/selectTicketsByUserId/${data.id}`,
        method: 'post',
        data
    })
}


export function getUseTickets(data) {
    return request({
        //设置请求数据格式为json
        url: '/api/ticket/selectListUseTicket',
        method: 'post',
        data
    })
}



export function addTicket(data) {
    return request({
        url: '/api/ticket/insert',
        //post请求
        method: 'post',
        data
    })
}


export function deleteTicket(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/ticket/deleteTicketById/${data.id}`,
        method: 'post',
        data
    })
}

export function deleteUseTicket(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/ticket/deleteUseTicketById/${data.id}`,
        method: 'post',
        data
    })
}

export function buyUserTicket(data) {
    return request({
        //设置请求数据格式为json
        url: `/api/ticket/buyUserTicket`,
        method: 'post',
        data
    })
}

export function getUseTicketByTicketId(data) {
    return request({
        //设置请求数据格式为json
        headers: {"Content-Type": "application/json;charset=UTF-8"},
        url: `/api/ticket/selectUseTicketByTicketId/${data.id}`,
        method: 'post',
        data
    })
}
