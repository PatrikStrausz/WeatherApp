package com.example.weatherapp.forecast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.OnForecastClick;
import com.example.weatherapp.R;
import com.example.weatherapp.weather.WeatherResult;
import com.example.weatherapp.WeatherViewModel;

import java.io.Serializable;

public class ForecastFragment extends Fragment implements Serializable, OnForecastClick {




    private WeatherViewModel weatherViewModel;
    private WeatherResult weatherResponse;
    private RecyclerView mRepositoryRecyclerView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        mRepositoryRecyclerView = view.findViewById(R.id.recycler_view);
        ForecastAdapter forecastAdapter = new ForecastAdapter(getContext());
        forecastAdapter.setListener(this);
        mRepositoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRepositoryRecyclerView.setAdapter(forecastAdapter);

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        weatherViewModel.getRepos().observe(requireActivity(), new Observer<WeatherResult>() {


            @Override
            public void onChanged(WeatherResult weatherResult) {
                if(weatherResult != null){
                    weatherResponse = weatherResult;
                    forecastAdapter.setmAllRepositories(weatherResult);


                    Log.d("Response", "onChanged: "+weatherResponse.getCityObject().getName());
                }else{
                    Log.d("Response", "null");
                }

            }
        });


        return view;
    }


  private void openForecastDetail(WeatherResult weatherResult, int index){
      Intent intent = new Intent(getActivity(), ForecastDetail.class);
      Bundle bundle = new Bundle();
      bundle.putSerializable("Response", weatherResult);
      bundle.putInt("Index", index);
      intent.putExtras(bundle);
      startActivity(intent);
  }

    @Override
    public void onForecastClick(WeatherResult weatherResult) {

        openForecastDetail(weatherResult, 0);
    }

    @Override
    public void onForecastSecondClick(WeatherResult weatherResult) {
        openForecastDetail(weatherResult, 8);
    }

    @Override
    public void onForecastThirdClick(WeatherResult weatherResult) {
        openForecastDetail(weatherResult, 16);
    }

    @Override
    public void onForecastFourthClick(WeatherResult weatherResult) {
        openForecastDetail(weatherResult, 24);
    }

    @Override
    public void onForecastFifthClick(WeatherResult weatherResult) {
        openForecastDetail(weatherResult, 32);
    }

    @Override
    public void onForecastSixthClick(WeatherResult weatherResult) {
        openForecastDetail(weatherResult, 33);
    }
}