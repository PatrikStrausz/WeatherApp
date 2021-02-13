package com.example.weatherapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.api.APIManager;
import com.example.weatherapp.weather.WeatherResult;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {


    private MutableLiveData<WeatherResult> mRepos = new MutableLiveData<>();
    private MutableLiveData<WeatherResult> location = new MutableLiveData<>();
    private Thread mThread;

    private WeatherViewModel.WeatherDao weatherDao;
    private LiveData<List<WeatherResult>> weatherList;

    private Application application;



    public LiveData<WeatherResult> getRepos() {
        return mRepos;
    }

    public MutableLiveData<WeatherResult> getLocation() {
        return location;
    }

    WeatherRepository(Application application) {
        this.application = application;
        WeatherViewModel.WeatherDatabase database = WeatherViewModel.WeatherDatabase.getDatabase(application);
        weatherDao = database.weatherDao();

        weatherList = weatherDao.getAllWeatherResults();
    }

    public LiveData<List<WeatherResult>> getWeatherList() {
        return weatherList;
    }


    void delete(WeatherResult weatherResult) {
        WeatherViewModel.WeatherDatabase.databaseWriteExecutor.execute(
                () -> {

                    weatherDao.deleteResultAndCity(weatherResult,weatherResult.getCityObject());
                }
        );
    }

    void insert(WeatherResult weatherResult) {
        WeatherViewModel.WeatherDatabase.databaseWriteExecutor.execute(
                () -> {
                    weatherDao.insertResultAndCity(weatherResult, weatherResult.getCityObject());

                }
        );
    }


    public void getWeatherByCoordinates(LatLng coordinates) {
        Runnable fetchJsonRunnable = new Runnable() {
            @Override
            public void run() {
                getCurrentWeatherByCoordinates(coordinates);
            }
        };


        if (mThread != null) {
            mThread.interrupt();
        }
        mThread = new Thread(fetchJsonRunnable);
        mThread.start();
    }

    public void getWeatherByCityName(String city) {
        Runnable fetchJsonRunnable = new Runnable() {
            @Override
            public void run() {
                getCurrentWeatherByCityName(city);
            }
        };


        if (mThread != null) {
            mThread.interrupt();
        }
        mThread = new Thread(fetchJsonRunnable);
        mThread.start();

    }


    void getCurrentWeatherByCoordinates(LatLng coordinates) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);


        //Call<WeatherResult> call = service.getCurrentWeather(latitude,longitude, APIManager.API_ID);
        Call<WeatherResult> call = service.getCurrentWeather(String.valueOf(coordinates.latitude), String.valueOf(coordinates.longitude), APIManager.API_ID, APIManager.UNITS);


        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                Log.d("Response", String.valueOf(response.code()));
                Log.d("Response", String.valueOf(response.message()));


                if (response.code() == 200) {
                    WeatherResult weatherResponse = response.body();

                    if (weatherResponse != null) {
                        mRepos.postValue(weatherResponse);
                    }

                }
                else if(response.code() >= 500){
                    Toast.makeText(application, "Server error, try again. ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {

                Log.d("Response", t.getMessage());
                Toast.makeText(application, "No Internet Connection", Toast.LENGTH_SHORT).show();




            }
        });
    }

    void getCurrentWeatherByCityName(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);


        Call<WeatherResult> call = service.getCurrentWeatherByCityName(cityName, APIManager.API_ID, APIManager.UNITS);


        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                Log.d("Response", String.valueOf(response.code()));


                if (response.code() == 200) {
                    WeatherResult weatherResponse = response.body();


                    if (weatherResponse != null) {


                        location.postValue(weatherResponse);


                    }

                }
                else if(response.code() == 404){
                    Toast.makeText(application, "City \""+cityName+"\" not found ", Toast.LENGTH_SHORT).show();

                }
                else if(response.code() >= 500){
                    Toast.makeText(application, "Server error, try again. ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {


                Log.d("Response", t.getMessage());
                Toast.makeText(application, "No Internet Connection", Toast.LENGTH_SHORT).show();



            }
        });


    }


}
