package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherResult implements Serializable {
    @SerializedName("cod")
    private String cod;
    @SerializedName("message")
    private int message;
    @SerializedName("cnt")
    private int cnt;
    @SerializedName("list")
    private List< WeatherList > list = new ArrayList<WeatherList>();
    @SerializedName("city")
    private City CityObject;

    public WeatherResult() {
    }

    public List<WeatherList> getList() {
        return list;
    }

    public void setList(List<WeatherList> list) {
        this.list = list;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public float getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public float getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public City getCityObject() {
        return CityObject;
    }

    public void setCityObject(City cityObject) {
        CityObject = cityObject;
    }
}
