package com.example.weatherapp.mylocation;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.DialogFragmentAdd;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.home.HomeAdapter;

import com.example.weatherapp.weather.WeatherResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MyLocationsFragment extends Fragment {


    private WeatherResult weatherResponse;
    private RecyclerView mRepositoryRecyclerView;

    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_locations, container, false);

        fab = view.findViewById(R.id.fab);

        mRepositoryRecyclerView = view.findViewById(R.id.recyclerView);
        LocationAdapter locationAdapter = new LocationAdapter(getContext());

        mRepositoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRepositoryRecyclerView.setAdapter(locationAdapter);

        ViewModelProvider provider = new ViewModelProvider(requireActivity());
        WeatherViewModel  weatherViewModel = provider.get(WeatherViewModel.class);

        weatherViewModel.getWeatherByCoordinates();
        weatherViewModel.getWeatherList().observe(requireActivity(), new Observer<List<WeatherResult>>() {


            @Override
            public void onChanged(List<WeatherResult> weatherResult) {
                if(weatherResult != null){



                    locationAdapter.setCachedWeather(weatherResult);


                }else{
                    Log.d("Response", "null");
                }

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFabClick();
            }
        });


        return view;
    }

    private void processFabClick() {

        DialogFragment dialogFragment = new DialogFragmentAdd(requireActivity());
        dialogFragment.show(getChildFragmentManager(),"insert");


    }
}