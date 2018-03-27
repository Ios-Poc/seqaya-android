package com.ntg.user.sa2aia.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

public class Order implements Serializable {

    private List<OrderItem> orderItemList;
    private String date;
    private String location;
    private @PaymentMethod
    String paymentMethod;
    private String time;


    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
