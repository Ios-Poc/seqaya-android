package com.ntg.user.sa2aia.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ilias on 28/03/2018.
 */

public class Location implements Serializable {

    private String userId;
    private String address;
    @SerializedName("lat")
    private double latitude;
    @SerializedName("long")
    private double longitude;

    public Location(String userId, String address, double latitude, double longitude) {
        this.userId = userId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
