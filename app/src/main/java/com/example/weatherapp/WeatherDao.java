package com.example.weatherapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.weatherapp.weather.City;
import com.example.weatherapp.weather.WeatherResult;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherResult weatherResult);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(City city);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResultAndCity(WeatherResult weatherResult,City city);

    @Delete
    void deleteResultAndCity(WeatherResult weatherResult,City city);

    @Query("SELECT  * from weather_result")
    LiveData<List<WeatherResult>> getAllWeatherResults();

    @Query("DELETE FROM weather_result")
    void deleteAll();





}
