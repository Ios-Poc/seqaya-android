package com.ntg.user.sa2aia.model;

import java.io.Serializable;

/**
 * Created by islam on 3/27/2018.
 */

public class ShoppingCartClient implements Serializable {
    private static ShoppingCart SHOPPING_CART;

    private ShoppingCartClient() {
    }

    public static ShoppingCart getShoppingCart() {
        if (SHOPPING_CART == null) {
            SHOPPING_CART = new ShoppingCart();
        }
        return SHOPPING_CART;
    }

}
