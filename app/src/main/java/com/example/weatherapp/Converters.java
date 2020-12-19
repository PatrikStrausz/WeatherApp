package com.example.weatherapp;

import androidx.room.TypeConverter;

import com.example.weatherapp.weather.City;
import com.example.weatherapp.weather.WeatherList;
import com.example.weatherapp.weather.WeatherResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    @TypeConverter
    public String fromList(List<WeatherList> weatherLists){
        if(weatherLists == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<WeatherList>>(){}.getType();
        return gson.toJson(weatherLists, type);
    }

    @TypeConverter
    public List<WeatherList> toWeatherList(String weatherString){
        if(weatherString == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<WeatherList>>(){}.getType();
        return gson.fromJson(weatherString, type);
    }

    @TypeConverter
    public String fromCity(City city){
        if(city == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<City>(){}.getType();
        return gson.toJson(city, type);
    }

    @TypeConverter
    public City toCity(String city){
        if(city == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<City>(){}.getType();
        return gson.fromJson(city, type);
    }
}