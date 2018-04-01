package com.ntg.user.sa2aia.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Sara Elmoghazy on 27/03/2018.
 */

public class ShoppingCart implements Serializable {
    private List<CartItem> cartItemList = new CopyOnWriteArrayList<>();

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
