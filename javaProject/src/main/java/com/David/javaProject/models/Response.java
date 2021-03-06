package com.David.javaProject.models;

import java.util.ArrayList;
import java.util.List;

public class Response {
    public boolean status;
    public String message;
    public List<?> data;
    public OrderDetail orderDetail;

    public Response(){

    }

    public Response(boolean status, String message, List<?> data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(boolean status, String message){
        this.status = status;
        this.message = message;
    }

    public Response(boolean status, String message, OrderDetail orderDetail) {
        this.status = status;
        this.message = message;
        this.orderDetail = orderDetail;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
