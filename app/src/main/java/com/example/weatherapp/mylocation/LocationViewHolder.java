package com.example.weatherapp.mylocation;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.CustomForecastClick;
import com.example.weatherapp.converters.DateFormatter;
import com.example.weatherapp.R;
import com.example.weatherapp.converters.WeatherImages;
import com.example.weatherapp.weather.WeatherResult;

public class LocationViewHolder extends RecyclerView.ViewHolder {

   private TextView dateTextView;
   private TextView maxTempTextView;
   private TextView minTempTextView;

   private ImageView weatherImage;

   private CardView cardView;

   private DateFormatter dateFormatter;

   private WeatherImages weatherImages;

    private CustomForecastClick listener;

    public void setListener(CustomForecastClick listener) {
        this.listener = listener;
    }

    public LocationViewHolder(@NonNull View view) {
        super(view);

        dateFormatter = new DateFormatter();
        weatherImages = new WeatherImages();

        cardView = view.findViewById(R.id.cardView);

        dateTextView =  view.findViewById(R.id.dateTextView);
        maxTempTextView = view.findViewById(R.id.maxTempTextView);
        minTempTextView = view.findViewById(R.id.minTempTextView);

        weatherImage = view.findViewById(R.id.ImageView);
    }

    public void bind(WeatherResult weatherResult, Context context){

        if(weatherResult != null) {
//        dateTextView.setText(dateFormatter.getCurrentTime(weatherResult.getList().get(0).getDt_txt()));
            dateTextView.setText(weatherResult.getCityObject().getName());
        maxTempTextView.setText(String.valueOf(Math.round(weatherResult.getList().get(0).getMainList().getTemp_max())) + "°");
        minTempTextView.setText("/" + String.valueOf(Math.round(weatherResult.getList().get(0).getMainList().getTemp_min())) + "°");

        weatherImages.setImage(weatherImage, weatherResult.getList().get(0).getWeatherList().get(0).getIcon(),context);
        weatherImages.setCardViewBackground(cardView, context, weatherResult.getList().get(0).getWeatherList().get(0).getIcon());



            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onForecastClick(weatherResult);

                }
            });
        }
    }
}
