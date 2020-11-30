package com.example.weatherapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weatherapp.weather.WeatherResult;

public class WeatherViewModel extends AndroidViewModel {


    private WeatherRepository repository = new WeatherRepository();
    private LiveData<WeatherResult> mRepos = repository.getRepos();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<WeatherResult> getRepos() {
        return mRepos;
    }

    public void getWeatherByCoordinates() {
        repository.getWeatherByCoordinates();
    }

    public void getWeatherByCityName() {
        repository.getWeatherByCityName();
    }

}
