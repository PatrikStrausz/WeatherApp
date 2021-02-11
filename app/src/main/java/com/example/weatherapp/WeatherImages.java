package com.example.weatherapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;

public class WeatherImages {


    public void setImage(final ImageView imageView, final String value, Context context) {
        switch (value) {
            case "01d":

                imageView.setImageDrawable(context.getDrawable(R.drawable.clear_sky));
                break;
            case "01n":
                imageView.setImageDrawable(context.getDrawable(R.drawable.clear_sky));
                break;
            case "02d":
                imageView.setImageDrawable(context.getDrawable(R.drawable.few_clouds));
                break;
            case "02n":
                imageView.setImageDrawable(context.getDrawable(R.drawable.few_clouds));
                break;
            case "03d":
                imageView.setImageDrawable(context.getDrawable(R.drawable.cloud));
                break;
            case "03n":
                imageView.setImageDrawable(context.getDrawable(R.drawable.cloud));
                break;
            case "04d":
                imageView.setImageDrawable(context.getDrawable(R.drawable.cloud));
                break;
            case "04n":
                imageView.setImageDrawable(context.getDrawable(R.drawable.cloud));
                break;
            case "09d":
                imageView.setImageDrawable(context.getDrawable(R.drawable.rain));
                break;
            case "09n":
                imageView.setImageDrawable(context.getDrawable(R.drawable.rain));
                break;
            case "10d":

                imageView.setImageDrawable(context.getDrawable(R.drawable.heavy_rain));
                break;
            case "10n":
                imageView.setImageDrawable(context.getDrawable(R.drawable.heavy_rain));
                break;
            case "11d":
                imageView.setImageDrawable(context.getDrawable(R.drawable.storm));
                break;
            case "11n":
                imageView.setImageDrawable(context.getDrawable(R.drawable.storm));
                break;
            case "13d":
                imageView.setImageDrawable(context.getDrawable(R.drawable.snow));
                break;
            case "13n":
                imageView.setImageDrawable(context.getDrawable(R.drawable.snow));
                break;
            case "50d":
                imageView.setImageDrawable(context.getDrawable(R.drawable.mist));
                break;
            default:
                imageView.setImageDrawable(context.getDrawable(R.drawable.cloud));

        }
    }

    public void setBackground(String timeOfDay, LinearLayout linearLayout, Context context){

        switch (timeOfDay){
            case "06:00":
                linearLayout.setBackground(context.getDrawable(R.drawable.background_b));
                break;
            case "09:00":
                linearLayout.setBackground(context.getDrawable(R.drawable.background_b));
                break;
            case "12:00":
                linearLayout.setBackground(context.getDrawable(R.drawable.background_c));
                break;
            case "15:00":
                linearLayout.setBackground(context.getDrawable(R.drawable.background_c));
                break;
            case "18:00":
                linearLayout.setBackground(context.getDrawable(R.drawable.background_d));
                break;
            case "21:00":
                linearLayout.setBackground(context.getDrawable(R.drawable.background_d));
                break;
            case "24:00":
                linearLayout.setBackground(context.getDrawable(R.drawable.background_night));
                break;
            case "03:00":
                linearLayout.setBackground(context.getDrawable(R.drawable.background_night));
                break;

        }
    }

    public void setCardViewBackground(CardView cardView, Context context, String value){
        switch (value) {
            case "01d":

                cardView.setBackground(context.getDrawable(R.drawable.sunny_widget));
                break;
            case "01n":
                cardView.setBackground(context.getDrawable(R.drawable.sunny_widget));
                break;
            case "02d":
                cardView.setBackground(context.getDrawable(R.drawable.cloudy_widget));
                break;
            case "02n":
                cardView.setBackground(context.getDrawable(R.drawable.cloudy_widget));
                break;
            case "03d":
                cardView.setBackground(context.getDrawable(R.drawable.cloudy_widget));
                break;
            case "03n":
                cardView.setBackground(context.getDrawable(R.drawable.cloudy_widget));
                break;
            case "04d":
                cardView.setBackground(context.getDrawable(R.drawable.cloudy_widget));
                break;
            case "04n":
                cardView.setBackground(context.getDrawable(R.drawable.cloudy_widget));
                break;
            case "09d":
                cardView.setBackground(context.getDrawable(R.drawable.rain_widget));
                break;
            case "09n":
                cardView.setBackground(context.getDrawable(R.drawable.rain_widget));
                break;
            case "10d":

                cardView.setBackground(context.getDrawable(R.drawable.rain_widget));
                break;
            case "10n":
                cardView.setBackground(context.getDrawable(R.drawable.rain_widget));
                break;
            case "11d":
                cardView.setBackground(context.getDrawable(R.drawable.rain_widget));
                break;
            case "11n":
                cardView.setBackground(context.getDrawable(R.drawable.rain_widget));
                break;
            case "13d":
                cardView.setBackground(context.getDrawable(R.drawable.widget_snow));
                break;
            case "13n":
                cardView.setBackground(context.getDrawable(R.drawable.widget_snow));
                break;
            case "50d":
                cardView.setBackground(context.getDrawable(R.drawable.cloudy_widget));
                break;
            default:
                cardView.setBackground(context.getDrawable(R.drawable.cloudy_widget));

        }
    }
}
