package com.ntg.user.sa2aia.model;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ilias on 27/03/2018.
 */

public class User implements Serializable {
    private static String name;
    private static String email;
    private static String password;
    private static ShoppingCart shoppingCart = new ShoppingCart();

    public static ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static void clearCart(){
        shoppingCart.getCartItemList().clear();
    }
}
