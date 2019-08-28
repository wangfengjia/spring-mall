package com.mall.common.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ErrorInfo<T>{

    public static final Integer ERROR = 100;
    public static final Integer PARAMS_ERROR = 110;
    public static final Integer DATA_NOT_FOUND = 404;

    private int code;

    private String message;

    private T data;

    private String url;

    public ErrorInfo(){}

    public ErrorInfo(int code, String message, T data, String url){
        this.code = code;
        this.message = message;
        this.data = data;
        this.url = url;
    }
}
