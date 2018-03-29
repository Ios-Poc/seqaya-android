package com.ntg.user.sa2aia.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

public class Order implements Serializable {

    private String id;
    private String userId;
    private List<CartItem> cartItems;
    private String deliveryDate;
    private String deliveryTime;
    private String location;
    private @PaymentMethod
    String paymentMethod;
    private @OrderStatus
    String status;
    private int total;

    public Order(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.status = OrderStatus.IN_PROCESSING;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(@OrderStatus String status) {
        this.status = status;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public void setPaymentMethod(@PaymentMethod String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


}
