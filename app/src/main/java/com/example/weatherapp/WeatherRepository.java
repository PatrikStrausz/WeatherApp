package com.example.weatherapp;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.weather.WeatherResult;

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

    private WeatherDao weatherDao;
    private LiveData<List<WeatherResult>> weatherList;

    private WeatherResult weatherResult = new WeatherResult();


    public LiveData<WeatherResult> getRepos(){
        return mRepos;
    }

    public MutableLiveData<WeatherResult> getLocation() {
        return location;
    }

    WeatherRepository(Application application){
        WeatherDatabase database = WeatherDatabase.getDatabase(application);
        weatherDao = database.weatherDao();

        weatherList = weatherDao.getAllVehicles();
    }

    public LiveData<List<WeatherResult>> getWeatherList() {
        return weatherList;
    }

    void insert(WeatherResult weatherResult){
        WeatherDatabase.databaseWriteExecutor.execute(
                ()->{
                    weatherDao.insert(weatherResult);
                }
        );
    }

    public void getWeatherByCoordinates(){
        Runnable fetchJsonRunnable = new Runnable() {
            @Override
            public void run() {
                getCurrentWeatherByCoordinates();
            }
        };


        if (mThread != null){
            mThread.interrupt();
        }
        mThread = new Thread(fetchJsonRunnable);
        mThread.start();
    }

    public void getWeatherByCityName(String city){
        Runnable fetchJsonRunnable = new Runnable() {
            @Override
            public void run() {
              getCurrentWeatherByCityName(city);
            }
        };


        if (mThread != null){
            mThread.interrupt();
        }
        mThread = new Thread(fetchJsonRunnable);
        mThread.start();

    }


    void getCurrentWeatherByCoordinates() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);


        //Call<WeatherResult> call = service.getCurrentWeather(latitude,longitude, APIManager.API_ID);
        Call<WeatherResult> call = service.getCurrentWeather("48.716385", "21.261074", APIManager.API_ID, APIManager.UNITS);


        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                Log.d("Response", String.valueOf(response.code()));
                Log.d("Response", String.valueOf(response.message()));



                if (response.code() == 200) {
                    WeatherResult weatherResponse = response.body();

                    if(weatherResponse != null){
                        mRepos.postValue(weatherResponse);
                    }

                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {


                Log.d("Response", t.getMessage());


            }
        });
    }

    void getCurrentWeatherByCityName(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);


        //Call<WeatherResult> call = service.getCurrentWeather(latitude,longitude, APIManager.API_ID);
        Call<WeatherResult> call = service.getCurrentWeatherByCityName(cityName, APIManager.API_ID, APIManager.UNITS);




        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                Log.d("Response", String.valueOf(response.code()));



                if (response.code() == 200) {
                    WeatherResult weatherResponse = response.body();
                    weatherResult = weatherResponse;

                    if(weatherResponse != null){
//

                        location.postValue(weatherResponse);


                    }




                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {


                Log.d("Response", t.getMessage());


            }
        });
//


    }



}
