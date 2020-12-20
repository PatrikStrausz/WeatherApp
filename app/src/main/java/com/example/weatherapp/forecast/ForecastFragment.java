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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.weatherapp.OnForecastClick;
import com.example.weatherapp.R;
import com.example.weatherapp.weather.WeatherResult;
import com.example.weatherapp.WeatherViewModel;

import java.io.Serializable;

public class ForecastFragment extends Fragment implements Serializable, OnForecastClick {




    private WeatherViewModel weatherViewModel;
    private RecyclerView mRepositoryRecyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ForecastAdapter forecastAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);

        mRepositoryRecyclerView = view.findViewById(R.id.recycler_view);
        forecastAdapter = new ForecastAdapter(getContext());
        forecastAdapter.setListener(this);
        mRepositoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRepositoryRecyclerView.setAdapter(forecastAdapter);

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);


        getForecast();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getForecast();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }

    private void getForecast(){
        weatherViewModel.getRepos().observe(requireActivity(), new Observer<WeatherResult>() {

            @Override
            public void onChanged(WeatherResult weatherResult) {
                if(weatherResult != null){
                    forecastAdapter.setmAllRepositories(weatherResult);


                    Log.d("Response", "onChanged: "+weatherResult.getCityObject().getName());
                }else{
                    Log.d("Response", "null");
                }

            }
        });
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