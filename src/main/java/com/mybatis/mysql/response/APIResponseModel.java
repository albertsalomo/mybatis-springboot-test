package com.mybatis.mysql.response;

import org.springframework.http.HttpStatus;

public class APIResponseModel<T> {
    private HttpStatus status;
    private String message;
    private T data;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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

    public void SetJSONResponseWithData(T data, String message, HttpStatus status) {
        setData(data);
        setMessage(message);
        setStatus(status);
    }

    public void SetJSONResponse(String message, HttpStatus status) {
        setMessage(message);
        setStatus(status);
    }
}