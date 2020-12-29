package com.example.weatherapp.weather;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapp.Converters;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "weather_result",indices = {@Index(value = {"id","city_id"}, unique = true)})
public class WeatherResult implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "cod")
    @SerializedName("cod")
    private String cod;

    @ColumnInfo(name = "message")
    @SerializedName("message")
    private int message;

    @ColumnInfo(name = "cnt")
    @SerializedName("cnt")
    private int cnt;

    @ColumnInfo(name = "list")
    @SerializedName("list")
    @TypeConverters({Converters.class})
    private List< WeatherList > list = new ArrayList<>();



    @ColumnInfo(name="city_id")
    private int city_id;

    @SerializedName("city")
    @TypeConverters({Converters.class})
    private City cityObject;

    public WeatherResult() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherList> getList() {
        return list;
    }

    public void setList(List<WeatherList> list) {
        this.list = list;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public City getCityObject() {
        return cityObject;
    }

    public void setCityObject(City cityObject) {
        this.cityObject = cityObject;
    }




}
