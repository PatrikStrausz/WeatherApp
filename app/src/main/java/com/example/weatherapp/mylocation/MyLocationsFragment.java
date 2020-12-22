package com.example.weatherapp.mylocation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.CustomForecastClick;
import com.example.weatherapp.DialogFragmentAdd;
import com.example.weatherapp.R;
import com.example.weatherapp.SwipeToDelete;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.forecast.ForecastDetail;
import com.example.weatherapp.home.HomeAdapter;

import com.example.weatherapp.weather.WeatherResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MyLocationsFragment extends Fragment implements CustomForecastClick {



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





        ViewModelProvider provider = new ViewModelProvider(requireActivity());
        WeatherViewModel weatherViewModel = provider.get(WeatherViewModel.class);


        mRepositoryRecyclerView = view.findViewById(R.id.recyclerView);
        LocationAdapter locationAdapter = new LocationAdapter(getActivity(), weatherViewModel);
        locationAdapter.setListener(this);

        mRepositoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRepositoryRecyclerView.setAdapter(locationAdapter);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDelete(locationAdapter, getContext()));
        itemTouchHelper.attachToRecyclerView(mRepositoryRecyclerView);

//        weatherViewModel.getWeatherByCoordinates();
        weatherViewModel.getWeatherList().observe(requireActivity(), new Observer<List<WeatherResult>>() {


            @Override
            public void onChanged(List<WeatherResult> weatherResult) {
                if (weatherResult != null) {

                        locationAdapter.setCachedWeather(weatherResult);

                    for (WeatherResult weatherResult1: weatherResult){
                        Log.d("TAG", weatherResult1.getCityObject().getName());
                    }

                } else {
                    Log.d("Response", "null");
                }

            }
        });

        weatherViewModel.getLocation().observe(requireActivity(), new Observer<WeatherResult>() {
            @Override
            public void onChanged(WeatherResult weatherResult) {

                    weatherViewModel.insert(weatherResult);







                Log.d("Dialog", String.valueOf(locationAdapter.getItemCount()));

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
        dialogFragment.show(getChildFragmentManager(), "insert");


    }

    @Override
    public void onForecastClick(WeatherResult weatherResult) {
        openForecastDetail(weatherResult);
    }

    private void openForecastDetail(WeatherResult weatherResult) {
        Intent intent = new Intent(getActivity(), ForecastDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Response", weatherResult);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}