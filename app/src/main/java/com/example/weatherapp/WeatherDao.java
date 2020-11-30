package com.example.weatherapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weatherapp.weather.WeatherResult;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherResult weatherResult);

    @Query("SELECT * from weather_result ")
    LiveData<List<WeatherResult>> getAllVehicles();

    @Query("DELETE FROM weather_result")
    void deleteAll();
}
