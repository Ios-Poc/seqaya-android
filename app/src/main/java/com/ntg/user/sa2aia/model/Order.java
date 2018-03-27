package com.ntg.user.sa2aia.model;

import java.io.Serializable;

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
    private String userName;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
