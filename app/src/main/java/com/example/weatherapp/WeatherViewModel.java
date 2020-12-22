package com.example.weatherapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherapp.weather.City;
import com.example.weatherapp.weather.WeatherResult;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {


    private WeatherRepository repository;
    private LiveData<WeatherResult> mRepos;

    private LiveData<WeatherResult> location;

    private LiveData<List<WeatherResult>> weatherList;

    public WeatherViewModel(Application application){
        super(application);

        repository = new WeatherRepository(application);
        weatherList = repository.getWeatherList();

        mRepos = repository.getRepos();

        location= repository.getLocation();
    }


    public LiveData<List<WeatherResult>> getWeatherList() {

        return weatherList;
    }

    public void insert(WeatherResult weatherResult){
        repository.insert(weatherResult);
    }



    public void delete(WeatherResult weatherResult){
        repository.delete(weatherResult);
    }

    public LiveData<WeatherResult> getLocation() {
        return location;
    }

    public LiveData<WeatherResult> getRepos() {
        return mRepos;
    }

    public void getWeatherByCoordinates(LatLng coordinates) {
        repository.getWeatherByCoordinates(coordinates);
    }

    public void getWeatherByCityName(String city) {
         repository.getWeatherByCityName(city);
    }

}
