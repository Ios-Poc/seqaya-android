package com.ntg.user.sa2aia.model;

import java.io.Serializable;

/**
 * Created by Sara Elmoghazy on 27/03/2018.
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
