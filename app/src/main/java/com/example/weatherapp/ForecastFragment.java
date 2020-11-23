package com.example.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastFragment extends Fragment {



    private TextView firstDayTextView;

    private TextView firstMaxTempTextView;

    private TextView firstMinTempTextView;

    private ImageView firstImage;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        firstDayTextView = view.findViewById(R.id.firstDayTextView);

        firstMaxTempTextView = view.findViewById(R.id.firstMaxTempTextView);

        firstMinTempTextView = view.findViewById(R.id.firstMinTempTextView);

        firstImage = view.findViewById(R.id.firstImageView);

        getForecast();

        return view;
    }




    void getForecast(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);


        //Call<WeatherResult> call = service.getCurrentWeather(latitude,longitude, APIManager.API_ID);
        Call<WeatherResult> call = service.getCurrentWeather("48.71395", "21.25808", APIManager.API_ID, APIManager.UNITS);


        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                Log.d("Response", String.valueOf(response.code()));



                if (response.code() == 200) {
                    WeatherResult weatherResponse = response.body();

                    assert weatherResponse != null;

                    firstDayTextView.setText(getCurrentTime(weatherResponse.getList().get(0).getDt_txt()));

                    firstMaxTempTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp_max()))+"°");
                    firstMinTempTextView.setText("/"+String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp_min()))+"°");


                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {


                Log.d("Response", t.getMessage());


            }
        });
    }


    private String  getCurrentTime(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
        Date myDate = null;
        try {
            myDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("EEE dd/MM");
        String sMyDate = sdf.format(myDate);
        sMyDate = sMyDate.replaceAll(" ", "\n");

        return sMyDate;



    }


}