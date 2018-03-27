package com.ntg.user.sa2aia.model;

import com.ntg.user.sa2aia.products.*;
import com.ntg.user.sa2aia.products.Product;

import java.io.Serializable;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

public class CartItem implements Serializable {

    private com.ntg.user.sa2aia.products.Product product;
    private int quantity;



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public com.ntg.user.sa2aia.products.Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
