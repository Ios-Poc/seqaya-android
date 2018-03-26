package com.ntg.user.sa2aia.model;

import java.io.Serializable;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

public class OrderItem implements Serializable {

    private Product product;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
