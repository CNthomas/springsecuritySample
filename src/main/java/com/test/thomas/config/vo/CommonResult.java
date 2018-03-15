package com.test.thomas.config.vo;

import org.springframework.http.HttpStatus;

import javax.crypto.MacSpi;

/**
 * Created by thomas on 2018/3/15.
 */
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;

    public CommonResult(){

    }
    public CommonResult(String message,T data){
        this.message = message;
        this.data = data;
        this.code = HttpStatus.OK.value();
    }
    public CommonResult(String message,T data,int code){
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
