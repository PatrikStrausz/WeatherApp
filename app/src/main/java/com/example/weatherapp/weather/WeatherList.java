package com.example.weatherapp.weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherList implements Serializable {
    @SerializedName("dt")
    private int dt;
    @SerializedName("main")
    private MainData mainList;
    @SerializedName("weather")
    private List<WeatherData> weatherList = new ArrayList<>();
    @SerializedName("wind")
    private WindData windList;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("pop")
    private double pop;
    @SerializedName("sys")
    private SysData sysList;
    @SerializedName("dt_txt")
    private String dt_txt;



    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public MainData getMainList() {
        return mainList;
    }

    public void setMainList(MainData mainList) {
        this.mainList = mainList;
    }

    public List<WeatherData> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<WeatherData> weatherList) {
        this.weatherList = weatherList;
    }

    public WindData getWindList() {
        return windList;
    }

    public void setWindList(WindData windList) {
        this.windList = windList;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public double getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public SysData getSysList() {
        return sysList;
    }

    public void setSysList(SysData sysList) {
        this.sysList = sysList;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}