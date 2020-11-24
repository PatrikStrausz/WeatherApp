package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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
import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;
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

public class ForecastFragment extends Fragment implements Serializable{


    private TextView firstDayTextView;
    private TextView secondDayTextView;
    private TextView thirdDayTextView;
    private TextView fourthDayTextView;
    private TextView fifthDayTextView;
    private TextView sixthDayTextView;

    private TextView firstMaxTempTextView;
    private TextView secondMaxTempTextView;
    private TextView thirdMaxTempTextView;
    private TextView fourthMaxTempTextView;
    private TextView fifthMaxTempTextView;
    private TextView sixthMaxTempTextView;

    private TextView firstMinTempTextView;
    private TextView secondMinTempTextView;
    private TextView thirdMinTempTextView;
    private TextView fourthMinTempTextView;
    private TextView fifthMinTempTextView;
    private TextView sixthMinTempTextView;

    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourthImage;
    private ImageView fifthImage;
    private ImageView sixthImage;

    private MaterialCardView cardView;
    private MaterialCardView secondCardView;

   private WeatherResult weatherResult;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        firstDayTextView = view.findViewById(R.id.firstDayTextView);
        secondDayTextView = view.findViewById(R.id.secondDayTxtView);
        thirdDayTextView = view.findViewById(R.id.thirdDayTxtView);
        fourthDayTextView = view.findViewById(R.id.fourthDayTxtView);
        fifthDayTextView = view.findViewById(R.id.fifthDayTextView);
        sixthDayTextView = view.findViewById(R.id.sixthDayTextView);

        firstMaxTempTextView = view.findViewById(R.id.firstMaxTempTextView);
        secondMaxTempTextView = view.findViewById(R.id.secondMaxTempTextView);
        thirdMaxTempTextView = view.findViewById(R.id.thirdMaxTempTextView);
        fourthMaxTempTextView = view.findViewById(R.id.fourthMaxTempTextView);
        fifthMaxTempTextView = view.findViewById(R.id.fifthMaxTempTextView);
        sixthMaxTempTextView = view.findViewById(R.id.sixthMaxTempTextView);

        firstMinTempTextView = view.findViewById(R.id.firstMinTempTextView);
        secondMinTempTextView = view.findViewById(R.id.secondMinTempTextView);
        thirdMinTempTextView = view.findViewById(R.id.thirdMinTempTextView);
        fourthMinTempTextView = view.findViewById(R.id.fourthMinTempTextView);
        fifthMinTempTextView = view.findViewById(R.id.fifthMinTempTextView);
        sixthMinTempTextView = view.findViewById(R.id.sixthMinTempTextView);

        firstImage = view.findViewById(R.id.firstImageView);
        secondImage = view.findViewById(R.id.secondImageView);
        thirdImage = view.findViewById(R.id.thirdImageView);
        fourthImage = view.findViewById(R.id.fourthImageView);
        fifthImage = view.findViewById(R.id.fifthImageView);
        sixthImage = view.findViewById(R.id.sixthImageView);

        cardView = view.findViewById(R.id.cardView);
        secondCardView = view.findViewById(R.id.secondCardView);









        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), ForecastDetail.class);
               Log.d("Response", weatherResult.getCityObject().getName());
               Bundle bundle = new Bundle();
               bundle.putSerializable("Response", weatherResult);
               bundle.putInt("Index", 0);
               intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        secondCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForecastDetail.class);
                Log.d("Response", weatherResult.getCityObject().getName());
                Bundle bundle = new Bundle();
                bundle.putSerializable("Response", weatherResult);
                bundle.putInt("Index", 8);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        getForecast();

        return view;
    }
    


    void getForecast() {
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



                  weatherResult = weatherResponse;

                    firstDayTextView.setText(getCurrentTime(weatherResponse.getList().get(0).getDt_txt()));
                    secondDayTextView.setText(getCurrentTime(weatherResponse.getList().get(8).getDt_txt()));
                    thirdDayTextView.setText(getCurrentTime(weatherResponse.getList().get(16).getDt_txt()));
                    fourthDayTextView.setText(getCurrentTime(weatherResponse.getList().get(24).getDt_txt()));
                    fifthDayTextView.setText(getCurrentTime(weatherResponse.getList().get(32).getDt_txt()));
                    sixthDayTextView.setText(getCurrentTime(weatherResponse.getList().get(39).getDt_txt()));

                    firstMaxTempTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp_max())) + "°");
                    secondMaxTempTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(8).getMainList().getTemp_max())) + "°");
                    thirdMaxTempTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(16).getMainList().getTemp_max())) + "°");
                    fourthMaxTempTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(24).getMainList().getTemp_max())) + "°");
                    fifthMaxTempTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(32).getMainList().getTemp_max())) + "°");
                    sixthMaxTempTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(39).getMainList().getTemp_max())) + "°");

                    firstMinTempTextView.setText("/" + String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp_min())) + "°");
                    secondMinTempTextView.setText("/" + String.valueOf(Math.round(weatherResponse.getList().get(8).getMainList().getTemp_min())) + "°");
                    thirdMinTempTextView.setText("/" + String.valueOf(Math.round(weatherResponse.getList().get(16).getMainList().getTemp_min())) + "°");
                    fourthMinTempTextView.setText("/" + String.valueOf(Math.round(weatherResponse.getList().get(24).getMainList().getTemp_min())) + "°");
                    fifthMinTempTextView.setText("/" + String.valueOf(Math.round(weatherResponse.getList().get(32).getMainList().getTemp_min())) + "°");
                    sixthMinTempTextView.setText("/" + String.valueOf(Math.round(weatherResponse.getList().get(39).getMainList().getTemp_min())) + "°");

                    setImage(firstImage  , weatherResponse.getList().get(0).getWeatherList().get(0).getIcon());
                    setImage(secondImage  , weatherResponse.getList().get(8).getWeatherList().get(0).getIcon());
                    setImage(thirdImage  , weatherResponse.getList().get(16).getWeatherList().get(0).getIcon());
                    setImage(fourthImage  , weatherResponse.getList().get(24).getWeatherList().get(0).getIcon());
                    setImage(fifthImage  , weatherResponse.getList().get(32).getWeatherList().get(0).getIcon());
                    setImage(sixthImage  , weatherResponse.getList().get(39).getWeatherList().get(0).getIcon());


                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {


                Log.d("Response", t.getMessage());


            }
        });
    }


    private String getCurrentTime(String date) {

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

    private void setImage(final ImageView imageView, final String value) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                switch (value) {
                    case "01d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.clear_sky));
                        break;
                    case "01n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.clear_sky));
                        break;
                    case "02d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.few_clouds));
                        break;
                    case "02n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.few_clouds));
                        break;
                    case "03d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
                        break;
                    case "03n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
                        break;
                    case "04d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
                        break;
                    case "04n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
                        break;
                    case "09d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                        break;
                    case "09n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                        break;
                    case "10d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.heavy_rain));
                        break;
                    case "10n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.heavy_rain));
                        break;
                    case "11d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.storm));
                        break;
                    case "11n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.storm));
                        break;
                    case "13d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                        break;
                    case "13n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                        break;
                    case "50d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.mist));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));

                }
            }
        });


    }
}