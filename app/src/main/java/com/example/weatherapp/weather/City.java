package com.example.weatherapp.weather;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapp.Converters;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "city_result", indices = {@Index(value = {"id", "name", "country"}, unique = true)})
public class City implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;


    @ColumnInfo(name = "result_id")
    @ForeignKey(entity = WeatherResult.class,
            parentColumns = "id",
            childColumns = "result_id",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
    deferred = true)
    private int result_id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "coord")
    @SerializedName("coord")
    @TypeConverters({Converters.class})
    private Coord CoordObject;

    @ColumnInfo(name = "country")
    @SerializedName("country")
    private String country;

    @ColumnInfo(name = "population")
    @SerializedName("population")
    private int population;

    @ColumnInfo(name = "timezone")
    @SerializedName("timezone")
    private int timezone;

    @ColumnInfo(name = "sunrise")
    @SerializedName("sunrise")
    private int sunrise;

    @ColumnInfo(name = "sunset")
    @SerializedName("sunset")
    private int sunset;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoordObject() {
        return CoordObject;
    }

    public void setCoordObject(Coord coordObject) {
        CoordObject = coordObject;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

}
