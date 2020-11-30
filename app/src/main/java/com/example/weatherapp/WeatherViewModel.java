package com.example.weatherapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherapp.weather.WeatherResult;

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


    public LiveData<WeatherResult> getLocation() {
        return location;
    }

    public LiveData<WeatherResult> getRepos() {
        return mRepos;
    }

    public void getWeatherByCoordinates() {
        repository.getWeatherByCoordinates();
    }

    public void getWeatherByCityName(String city) {
         repository.getWeatherByCityName(city);
    }

}
