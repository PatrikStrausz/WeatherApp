package com.example.weatherapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ForecastDetail extends AppCompatActivity implements Serializable {

    private WeatherResult weatherResult;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        weatherResult = (WeatherResult) extras.getSerializable("Response");

        index = extras.getInt("Index", 0);



        Log.d("Response", weatherResult.getCityObject().getCountry());
        Log.d("Response",String.valueOf(index));

    }


}