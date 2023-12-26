package com.quickpay.jedco.model.response;


public class ResponseDTO<T> {
    private String message;
    private int count;
    private int status;
    private   T data;
    private   T data2;

    public T getData2() {
        return data2;
    }

    public String getMessage() {
        return message;
    }


    public int getCount() {
        return count;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

}
