package com.ntg.user.sa2aia.model;

import com.google.gson.annotations.SerializedName;

public class Fav {

    private String userId;
    @SerializedName("id")
    private String productId;

    public Fav(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
