package com.ntg.user.sa2aia.Checkout;

/**
 * Created by islam on 3/26/2018.
 */

public class Product {

    private int id;
    private String name;
    private String manufacturer;
    private Float bottleSize;
    private int no_bpp;
    private Float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Float getBottleSize() {
        return bottleSize;
    }

    public void setBottleSize(Float bottleSize) {
        this.bottleSize = bottleSize;
    }

    public int getNo_bpp() {
        return no_bpp;
    }

    public void setNo_bpp(int no_bpp) {
        this.no_bpp = no_bpp;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
