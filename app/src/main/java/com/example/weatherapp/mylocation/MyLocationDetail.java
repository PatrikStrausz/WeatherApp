package com.example.weatherapp.mylocation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;
import com.example.weatherapp.weather.WeatherResult;

import java.io.Serializable;

public class MyLocationDetail extends AppCompatActivity implements Serializable {



    private WeatherResult weatherResult;

    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_layout);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        weatherResult = (WeatherResult) extras.getSerializable("Response");

        index = extras.getInt("Index", 0);
    }


}
