package com.example.weatherapp.mylocation.detail.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.R;
import com.example.weatherapp.weather.WeatherResult;


public class NextFiveDaysFragment extends Fragment {


    private WeatherResult weatherResult;

    public NextFiveDaysFragment(WeatherResult weatherResult) {
        this.weatherResult = weatherResult;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_five_days, container, false);
    }
}