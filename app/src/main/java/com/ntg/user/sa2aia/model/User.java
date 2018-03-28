package com.ntg.user.sa2aia.model;

import java.io.Serializable;

/**
 * Created by ilias on 27/03/2018.
 */

public class User implements Serializable {

    private static User CURRENT;
    private String name;
    private String email;
    private String password;
    private ShoppingCart shoppingCart = new ShoppingCart();

    public User(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public static void setCurrentUser(User user) {
        if (user != null)
            CURRENT = user;
    }

    public static User getCurrentUser() {
        return CURRENT;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
