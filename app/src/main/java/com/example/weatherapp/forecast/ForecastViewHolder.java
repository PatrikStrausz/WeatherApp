package com.example.weatherapp.forecast;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.converters.DateFormatter;
import com.example.weatherapp.OnForecastClick;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherImages;
import com.example.weatherapp.weather.WeatherResult;
import com.google.android.material.card.MaterialCardView;

public class ForecastViewHolder  extends RecyclerView.ViewHolder{

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
    private MaterialCardView thirdCardView;
    private MaterialCardView fourthCardView;
    private MaterialCardView fifthCardView;
    private MaterialCardView sixthCardView;

    private WeatherResult weatherResult;

    private DateFormatter dateFormatter;

    private WeatherImages weatherImages;


    private OnForecastClick listener;

    public void setListener(OnForecastClick listener) {
        this.listener = listener;
    }

    public ForecastViewHolder(@NonNull View view) {
        super(view);

        weatherImages = new WeatherImages();

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
        thirdCardView = view.findViewById(R.id.thirdCardView);
        fourthCardView = view.findViewById(R.id.fourthCardView);
        fifthCardView = view.findViewById(R.id.fifthCardView);
        sixthCardView = view.findViewById(R.id.sixthCardView);

        dateFormatter = new DateFormatter();
    }

    public void bind(WeatherResult weatherResponse, Context context){

        weatherResult = weatherResponse;

        firstDayTextView.setText(dateFormatter.getCurrentTime(weatherResponse.getList().get(0).getDt_txt()));
        secondDayTextView.setText(dateFormatter.getCurrentTime(weatherResponse.getList().get(8).getDt_txt()));
        thirdDayTextView.setText(dateFormatter.getCurrentTime(weatherResponse.getList().get(16).getDt_txt()));
        fourthDayTextView.setText(dateFormatter.getCurrentTime(weatherResponse.getList().get(24).getDt_txt()));
        fifthDayTextView.setText(dateFormatter.getCurrentTime(weatherResponse.getList().get(32).getDt_txt()));
        sixthDayTextView.setText(dateFormatter.getCurrentTime(weatherResponse.getList().get(39).getDt_txt()));

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

        weatherImages.setImage(firstImage, weatherResponse.getList().get(0).getWeatherList().get(0).getIcon(),context);
        weatherImages.setImage(secondImage, weatherResponse.getList().get(8).getWeatherList().get(0).getIcon(),context);
        weatherImages.setImage(thirdImage, weatherResponse.getList().get(16).getWeatherList().get(0).getIcon(),context);
        weatherImages.setImage(fourthImage, weatherResponse.getList().get(24).getWeatherList().get(0).getIcon(),context);
        weatherImages.setImage(fifthImage, weatherResponse.getList().get(32).getWeatherList().get(0).getIcon(),context);
        weatherImages.setImage(sixthImage, weatherResponse.getList().get(39).getWeatherList().get(0).getIcon(),context);


        weatherImages.setCardViewBackground(cardView, context, weatherResponse.getList().get(0).getWeatherList().get(0).getIcon());
        weatherImages.setCardViewBackground(secondCardView, context, weatherResponse.getList().get(8).getWeatherList().get(0).getIcon());
        weatherImages.setCardViewBackground(thirdCardView, context, weatherResponse.getList().get(16).getWeatherList().get(0).getIcon());
        weatherImages.setCardViewBackground(fourthCardView, context, weatherResponse.getList().get(24).getWeatherList().get(0).getIcon());
        weatherImages.setCardViewBackground(fifthCardView, context, weatherResponse.getList().get(32).getWeatherList().get(0).getIcon());
        weatherImages.setCardViewBackground(sixthCardView, context, weatherResponse.getList().get(39).getWeatherList().get(0).getIcon());


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onForecastClick(weatherResult);

            }
        });

        secondCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onForecastSecondClick(weatherResult);
            }
        });

        thirdCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onForecastThirdClick(weatherResult);
            }
        });

        fourthCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onForecastFourthClick(weatherResult);
            }
        });

        fifthCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onForecastFifthClick(weatherResult);
            }
        });

        sixthCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onForecastSixthClick(weatherResult);
            }
        });
    }
}
