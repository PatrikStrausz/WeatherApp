package com.example.weatherapp.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.converters.DateFormatter;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherImages;
import com.example.weatherapp.weather.WeatherResult;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    public  TextView cityNameTextView;
    private final TextView currentTemperatureTextView;
    private final TextView currentWeatherTextView;
    private final TextView dayTextView;
    private final TextView monthTextView;
    private final TextView timeTextView;

    private final TextView todayTempTextView;
    private final TextView secondTemp;
    private final TextView thirdTemp;
    private final TextView fourthTemp;
    private final TextView fifthTemp;
    private final TextView sixthTemp;

    private final TextView firstDateTxtView;
    private final TextView secondDateTxtView;
    private final TextView thirdDateTxtView;
    private final TextView fouthDateTxtView;
    private final TextView fifthDateTxtView;
    private final TextView sixthDateTxtView;

    private final TextView windSpeedTextView;
    private final TextView feelsLikeTextView;
    private final TextView humidityTextView;
    private final TextView visibilityTextView;
    private final TextView pressureTextView;
    private final TextView tempMinTextView;
    private final TextView tempMaxTextView;
    private final TextView windDirectionTextView;

    private final TextView firstHourTextView;
    private final TextView secondHourTextView;
    private final TextView thirdHourTextView;
    private final TextView fourthHourTextView;
    private final TextView fifthHourTextView;
    private final TextView sixthHourTextView;
    private final TextView seventhHourTextView;

    private final TextView firstHourTemp;
    private final TextView secondHourTemp;
    private final TextView thirdHourTemp;
    private final TextView fourthHourTemp;
    private final TextView fifthHourTemp;
    private final TextView sixthHourTemp;
    private final TextView seventhHourTemp;

    private final ImageView todayImageWeather;
    private final ImageView secondImageView;
    private final ImageView thirdImageView;
    private final ImageView fourthImageView;
    private final ImageView fifthImageView;
    private final ImageView sixthImageView;

    private final ImageView firstHourImage;
    private final ImageView secondHourImage;
    private final ImageView thirdHourImage;
    private final ImageView fourthHourImage;
    private final ImageView fifthHourImage;
    private final ImageView sixthHourImage;
    private final ImageView seventhHourImage;

    private final DateFormatter dateFormatter;

    private final WeatherImages weatherImages;

    private final LinearLayout linearLayout;



    public HomeViewHolder(@NonNull View view) {
        super(view);

        dateFormatter = new DateFormatter();

        weatherImages = new WeatherImages();

        dayTextView = view.findViewById(R.id.dayTextView);
        monthTextView = view.findViewById(R.id.monthTextView);
        timeTextView = view.findViewById(R.id.timeTextView);
        cityNameTextView = view.findViewById(R.id.cityNameTextView);
        currentTemperatureTextView = view.findViewById(R.id.currentTemperatureTextView);
        currentWeatherTextView = view.findViewById(R.id.currentWeatherTextView);

        firstDateTxtView = view.findViewById(R.id.firstDateTxtView);
        secondDateTxtView = view.findViewById(R.id.secondDateTxtView);
        thirdDateTxtView = view.findViewById(R.id.thirdDateTxtView);
        fouthDateTxtView = view.findViewById(R.id.fourthDateTxtView);
        fifthDateTxtView = view.findViewById(R.id.fifthDateTxtView);
        sixthDateTxtView = view.findViewById(R.id.sixthDateTxtView);

        linearLayout = view.findViewById(R.id.linearLayout);

        todayTempTextView = view.findViewById(R.id.firstTemp);
        secondTemp = view.findViewById(R.id.secondTemp);
        thirdTemp = view.findViewById(R.id.thirdTemp);
        fourthTemp = view.findViewById(R.id.fourthTemp);
        fifthTemp = view.findViewById(R.id.fifthTemp);
        sixthTemp = view.findViewById(R.id.sixthTemp);


        todayImageWeather = view.findViewById(R.id.firstImageView);
        secondImageView = view.findViewById(R.id.secondImageView);
        thirdImageView = view.findViewById(R.id.thirdImageView);
        fourthImageView = view.findViewById(R.id.fourthImageView);
        fifthImageView = view.findViewById(R.id.fifthImageView);
        sixthImageView = view.findViewById(R.id.sixthImageView);

        windSpeedTextView = view.findViewById(R.id.windSpeedTextView);
        feelsLikeTextView = view.findViewById(R.id.feelsLikeTextView);
        humidityTextView = view.findViewById(R.id.humidityTextView);
        visibilityTextView = view.findViewById(R.id.visibilityTextView);
        pressureTextView = view.findViewById(R.id.pressureTextView);
        tempMinTextView = view.findViewById(R.id.tempMinTextView);
        tempMaxTextView = view.findViewById(R.id.tempMaxTextView);
        windDirectionTextView = view.findViewById(R.id.windDirectionTextView);


        firstHourTextView = view.findViewById(R.id.firstHourTextView);
        secondHourTextView = view.findViewById(R.id.secondHourTextView);
        thirdHourTextView = view.findViewById(R.id.thirdHourTextView);
        fourthHourTextView = view.findViewById(R.id.fourthHourTextView);
        fifthHourTextView = view.findViewById(R.id.fifthHourTextView);
        sixthHourTextView = view.findViewById(R.id.sixthHourTextView);
        seventhHourTextView = view.findViewById(R.id.seventhHourTextView);

        firstHourImage = view.findViewById(R.id.firstHourImage);
        secondHourImage = view.findViewById(R.id.secondHourImage);
        thirdHourImage = view.findViewById(R.id.thirdHourImage);
        fourthHourImage = view.findViewById(R.id.fourthHourImage);
        fifthHourImage = view.findViewById(R.id.fifthHourImage);
        sixthHourImage = view.findViewById(R.id.sixthHourImage);
        seventhHourImage = view.findViewById(R.id.seventhHourImage);


        firstHourTemp = view.findViewById(R.id.firstHourTemp);
        secondHourTemp = view.findViewById(R.id.secondHourTemp);
        thirdHourTemp = view.findViewById(R.id.thirdHourTemp);
        fourthHourTemp = view.findViewById(R.id.fourthHourTemp);
        fifthHourTemp = view.findViewById(R.id.fifthHourTemp);
        sixthHourTemp = view.findViewById(R.id.sixthHourTemp);
        seventhHourTemp = view.findViewById(R.id.seventhHourTemp);
    }

    @SuppressLint("SetTextI18n")
    public void bind(WeatherResult weatherResult, Context context) {


        cityNameTextView.setText(weatherResult.getCityObject().getName());
        currentTemperatureTextView.setText(Math.round(weatherResult.getList().get(0).getMainList().getTemp()) + "°C");
        currentWeatherTextView.setText(weatherResult.getList().get(0).getWeatherList().get(0).getMain());

            weatherImages.setBackground(dateFormatter.getHour(weatherResult.getList().get(0).getDt_txt()),linearLayout,context);



        weatherImages.setImage(todayImageWeather, weatherResult.getList().get(0).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(secondImageView, weatherResult.getList().get(8).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(thirdImageView, weatherResult.getList().get(16).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(fourthImageView, weatherResult.getList().get(24).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(fifthImageView, weatherResult.getList().get(32).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(sixthImageView, weatherResult.getList().get(39).getWeatherList().get(0).getIcon(), context);
//

        todayTempTextView.setText(Math.round(weatherResult.getList().get(0).getMainList().getTemp_max()) + "°");
        secondTemp.setText(Math.round(weatherResult.getList().get(8).getMainList().getTemp_max()) + "°");
        thirdTemp.setText(Math.round(weatherResult.getList().get(16).getMainList().getTemp_max()) + "°");
        fourthTemp.setText(Math.round(weatherResult.getList().get(24).getMainList().getTemp_max()) + "°");
        fifthTemp.setText(Math.round(weatherResult.getList().get(32).getMainList().getTemp_max()) + "°");
        sixthTemp.setText(Math.round(weatherResult.getList().get(39).getMainList().getTemp_max()) + "°");


        firstDateTxtView.setText(dateFormatter.getDay(weatherResult.getList().get(0).getDt_txt()));
        secondDateTxtView.setText(dateFormatter.getDay(weatherResult.getList().get(8).getDt_txt()));
        thirdDateTxtView.setText(dateFormatter.getDay(weatherResult.getList().get(16).getDt_txt()));
        fouthDateTxtView.setText(dateFormatter.getDay(weatherResult.getList().get(24).getDt_txt()));
        fifthDateTxtView.setText(dateFormatter.getDay(weatherResult.getList().get(32).getDt_txt()));
        sixthDateTxtView.setText(dateFormatter.getDay(weatherResult.getList().get(39).getDt_txt()));


        windSpeedTextView.setText("Wind speed\n" + +Math.round(weatherResult.getList().get(0).getWindList().getSpeed()) + "km/h");
        feelsLikeTextView.setText("Feels like\n" + Math.round(weatherResult.getList().get(0).getMainList().getFeels_like()) + "°");
        humidityTextView.setText("Humidity\n" + weatherResult.getList().get(0).getMainList().getHumidity() + "%");
        visibilityTextView.setText("Visibility\n" + weatherResult.getList().get(0).getVisibility() / 1000 + "km");
        pressureTextView.setText("Pressure\n" + weatherResult.getList().get(0).getMainList().getPressure() + "hPa");
        tempMinTextView.setText("Min temperature\n" + Math.round(weatherResult.getList().get(0).getMainList().getTemp_min()) + "°");
        tempMaxTextView.setText("Max temperature\n" + Math.round(weatherResult.getList().get(0).getMainList().getTemp_max()) + "°");
        windDirectionTextView.setText("Wind direction\n" + weatherResult.getList().get(0).getWindList().getDeg());

        firstHourTextView.setText(dateFormatter.getHour(weatherResult.getList().get(0).getDt_txt()));
        secondHourTextView.setText(dateFormatter.getHour(weatherResult.getList().get(1).getDt_txt()));
        thirdHourTextView.setText(dateFormatter.getHour(weatherResult.getList().get(2).getDt_txt()));
        fourthHourTextView.setText(dateFormatter.getHour(weatherResult.getList().get(3).getDt_txt()));
        fifthHourTextView.setText(dateFormatter.getHour(weatherResult.getList().get(4).getDt_txt()));
        sixthHourTextView.setText(dateFormatter.getHour(weatherResult.getList().get(5).getDt_txt()));
        seventhHourTextView.setText(dateFormatter.getHour(weatherResult.getList().get(6).getDt_txt()));


        weatherImages.setImage(firstHourImage, weatherResult.getList().get(0).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(secondHourImage, weatherResult.getList().get(1).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(thirdHourImage, weatherResult.getList().get(2).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(fourthHourImage, weatherResult.getList().get(3).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(fifthHourImage, weatherResult.getList().get(4).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(sixthHourImage, weatherResult.getList().get(5).getWeatherList().get(0).getIcon(), context);
        weatherImages.setImage(seventhHourImage, weatherResult.getList().get(6).getWeatherList().get(0).getIcon(), context);
//

        firstHourTemp.setText(Math.round(weatherResult.getList().get(0).getMainList().getTemp()) + "°");
        secondHourTemp.setText(Math.round(weatherResult.getList().get(1).getMainList().getTemp()) + "°");
        thirdHourTemp.setText(Math.round(weatherResult.getList().get(2).getMainList().getTemp()) + "°");
        fourthHourTemp.setText(Math.round(weatherResult.getList().get(3).getMainList().getTemp()) + "°");
        fifthHourTemp.setText(Math.round(weatherResult.getList().get(4).getMainList().getTemp()) + "°");
        sixthHourTemp.setText(Math.round(weatherResult.getList().get(5).getMainList().getTemp()) + "°");
        seventhHourTemp.setText(Math.round(weatherResult.getList().get(6).getMainList().getTemp()) + "°");


        dateFormatter.getCurrentTime(dayTextView, monthTextView, timeTextView);



    }
}