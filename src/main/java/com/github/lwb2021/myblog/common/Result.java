package com.github.lwb2021.myblog.common;

import lombok.Data;

@Data
public class Result <T>{
    /*
    负数为失败
    0 操作成功
    1 操作开始
    -100 账号没有权限、错误
    -101 账号无效（账号或密码错误）
    -102 账号已存在（注册失败）
    -103 账号等级不足
    -104 账号未登录
    -105 账号已锁定

    -400 客户端参数错误
    -401 意外空值

    -500 服务器抛出异常
    -501 IO错误
     */
    private int code;
    private boolean succeed;
    private String message;
    private T data;
    private Result(int code, boolean succeed, String message, T data) {
        this.code = code;
        this.succeed = succeed;
        this.message = message;
        this.data = data;
    }
    public static <T> Result<T> succeed(int code, String message, T data){
        return new Result<>(code, true, message, data);
    }
    public static <T> Result<T> failed(int code, String message, T data){
        return new Result<>(code, false, message, data);
    }
}
