package com.ntg.user.sa2aia.model;

import java.io.Serializable;

/**
 * Created by islam on 3/26/2018.
 */

public class Product implements Serializable {

    private int id;
    private String name;
    private String manufacturer;
    private String photoUrl;
    private float bottleSize;
    private int no_bpp;
    private int price;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }


    public int getNo_bpp() {
        return no_bpp;
    }

    public int getPrice() {
        return price;
    }

    public float getBottleSize() {
        return bottleSize;
    }

    public void setBottleSize(float bottleSize) {
        this.bottleSize = bottleSize;
    }
}
