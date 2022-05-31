package com.github.lwb2021.myblog.common.exceptions;

import cn.dev33.satoken.exception.NotLoginException;
import com.github.lwb2021.myblog.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public Result<?> handle(RuntimeException runtimeException){
        runtimeException.printStackTrace();
        return Result.failed(-500,runtimeException.getClass().getName()
                + runtimeException.getMessage(), null);
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoginException.class)
    public Result<?> handle(NotLoginException runtimeException){
        runtimeException.printStackTrace();
        return Result.failed(-104, runtimeException.getMessage(), null);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handle(IllegalArgumentException runtimeException){
        if(runtimeException instanceof NumberFormatException){
            runtimeException.printStackTrace();
        }
        return Result.failed(-400, runtimeException.getMessage(), null);
    }
    @ExceptionHandler(CustomHttpException.class)
    public Result<?> handle(CustomHttpException exception, HttpServletResponse response){
        response.setStatus(exception.getCode());
        return exception.getResult();
    }
}
