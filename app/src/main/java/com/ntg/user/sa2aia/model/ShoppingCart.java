package com.ntg.user.sa2aia.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by islam on 3/27/2018.
 */

public class ShoppingCart implements Serializable {
    private List<CartItem> cartItemList;

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
