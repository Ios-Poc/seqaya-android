package com.ntg.user.sa2aia.model;

import android.net.Uri;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

public class Order implements Serializable {

    private ShoppingCartClient shoppingCartClient;
    private String date;
    private String location;
    private @PaymentMethod
    String paymentMethod;
    private String time;
    private String userId;


    public List<Product> getProducts() {
        return products;
    }

    List<Product> products;


    public void setExpectedDeliveryTime(String expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    private String expectedDeliveryTime;

    public String getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }
    public String getOrderId() {
        return orderId;
    }

    private String orderId;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    private String orderStatus;


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

    public ShoppingCartClient getShoppingCartClient() {
        return shoppingCartClient;
    }

    public void setShoppingCartClient(ShoppingCartClient shoppingCartClient) {
        this.shoppingCartClient = shoppingCartClient;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
