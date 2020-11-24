package com.example.weatherapp;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ForecastDetail extends AppCompatActivity implements Serializable {

    private WeatherResult weatherResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);

//        weatherResult = getIntent().getParcelableExtra("Response");
//
//        Log.d("Response", weatherResult.getCityObject().getName());

    }
}