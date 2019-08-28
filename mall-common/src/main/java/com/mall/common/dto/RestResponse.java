package com.mall.common.dto;

import lombok.Data;

@Data
public class RestResponse<T> {

    private int code = 0;

    //未授权
    private static final Integer FOBBIDEN = 403;
    //未认证
    private static final Integer UNAUTHORIZED = 401;

    private String message;

    private T payload;

    private long timestamps;

    private Boolean success;

    public RestResponse() {
        this.timestamps = System.currentTimeMillis() / 1000;
    }

    public RestResponse(Boolean success) {
        this.timestamps = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public RestResponse(Boolean success, T payload) {
        this.timestamps = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public RestResponse(Boolean success, T payload, int code) {
        this.timestamps = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public RestResponse(Boolean success, String message) {
        this.timestamps = System.currentTimeMillis() / 1000;
        this.success = success;
        this.message = message;
    }

    public RestResponse(Boolean success, String message, int code) {
        this.timestamps = System.currentTimeMillis() / 1000;
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public RestResponse(Boolean success, T payload, String message, int code){
        this.timestamps = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.message = message;
        this.code = code;
    }

    public static RestResponse success() {
        return new RestResponse(true);
    }

    public static <T> RestResponse success(T payload) {
        return new RestResponse<>(true, payload);
    }

    public static <T> RestResponse success(int code) {
        return new RestResponse(true, null, code);
    }

    public static <T> RestResponse success(T payload, int code) {
        return new RestResponse<>(true, payload, code);
    }

    public static RestResponse fail(){
        return new RestResponse(false);
    }

    public static RestResponse fail(int code){
        return new RestResponse(false, null, code);
    }

    public static RestResponse fail(String message){
        return new RestResponse(false, message);
    }

    public static RestResponse fail(String message, int code){
        return new RestResponse(false, message, code);
    }

    public static <T> RestResponse fobbiden(T payload){
        return new RestResponse<>(false, payload, "没有相关权限", FOBBIDEN);
    }

    public static <T> RestResponse unauthorized(T payload){
        return new RestResponse<>(false, payload, "暂未登录或token已经过期", UNAUTHORIZED);
    }
}
