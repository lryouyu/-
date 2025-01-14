package com.ysu.common;

import lombok.Getter;

@Getter
public class JsonResult<T> {
    //状态码 0表示成功 大于0表示失败 -1表示未登录或者登录过期
    private Integer state;
    //错误消息，后端处理失败后 错误消息
    private String msg;
    //返回结果集，操作结果正确，返回的数据
    private T data;

    public JsonResult(Integer state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }
}
