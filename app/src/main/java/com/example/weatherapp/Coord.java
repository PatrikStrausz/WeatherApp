package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Coord implements Serializable {

    @SerializedName("lat")
    private double lat;
    @SerializedName("lon")
    private double lon;


    // Getter Methods

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    // Setter Methods

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }


}