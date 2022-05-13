package com.github.lwb2021.myblog.common.exceptions;

import com.github.lwb2021.myblog.common.Result;

public class CustomHttpException extends RuntimeException {
    private int code;
    private Result<?> result;
    public CustomHttpException(int code, Result<?> result){
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public Result<?> getResult() {
        return result;
    }
}
