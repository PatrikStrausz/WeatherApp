package com.example.weatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class HomeFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationClient;

    private LinearLayout linearLayout;

    private String latitude;
    private String longitude;

    private TextView location_text;
    private TextView cityNameTextView;
    private TextView currentTemperatureTextView;
    private TextView currentWeatherTextView;
    private TextView dayTextView;
    private TextView monthTextView;
    private TextView timeTextView;

    private TextView todayTempTextView;
    private TextView secondTemp;
    private TextView thirdTemp;
    private TextView fourthTemp;
    private TextView fifthTemp;
    private TextView sixthTemp;

    private TextView firstDateTxtView;
    private TextView secondDateTxtView;
    private TextView thirdDateTxtView;
    private TextView fouthDateTxtView;
    private TextView fifthDateTxtView;
    private TextView sixthDateTxtView;

    private TextView windSpeedTextView;
    private TextView feelsLikeTextView;
    private TextView humidityTextView;
    private TextView visibilityTextView;
    private TextView pressureTextView;
    private TextView tempMinTextView;
    private TextView tempMaxTextView;
    private TextView windDirectionTextView;

    private TextView firstHourTextView;
    private TextView secondHourTextView;
    private TextView thirdHourTextView;
    private TextView fourthHourTextView;
    private TextView fifthHourTextView;
    private TextView sixthHourTextView;
    private TextView seventhHourTextView;

    private TextView firstHourTemp;
    private TextView secondHourTemp;
    private TextView thirdHourTemp;
    private TextView fourthHourTemp;
    private TextView fifthHourTemp;
    private TextView sixthHourTemp;
    private TextView seventhHourTemp;


    private EditText editText;

    private Button button;

    private ImageView todayImageWeather;
    private ImageView secondImageView;
    private ImageView thirdImageView;
    private ImageView fourthImageView;
    private ImageView fifthImageView;
    private ImageView sixthImageView;

    private ImageView firstHourImage;
    private ImageView secondHourImage;
    private ImageView thirdHourImage;
    private ImageView fourthHourImage;
    private ImageView fifthHourImage;
    private ImageView sixthHourImage;
    private ImageView seventhHourImage;




    private FusedLocationProviderClient fusedLocationProviderClient;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);



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

        editText = view.findViewById(R.id.editText);

        button = view.findViewById(R.id.getLocation);

        todayImageWeather = view.findViewById(R.id.firstImageView);
        secondImageView = view.findViewById(R.id.secondImageView);
        thirdImageView = view.findViewById(R.id.thirdImageView);
        fourthImageView = view.findViewById(R.id.fourthImageView);
        fifthImageView = view.findViewById(R.id.fifthImageView);
        sixthImageView = view.findViewById(R.id.sixthImageView);

        windSpeedTextView    = view.findViewById(R.id.windSpeedTextView);
        feelsLikeTextView    = view.findViewById(R.id.feelsLikeTextView) ;
        humidityTextView     = view.findViewById(R.id.humidityTextView) ;
        visibilityTextView   = view.findViewById(R.id.visibilityTextView)  ;
        pressureTextView     = view.findViewById(R.id.pressureTextView) ;
        tempMinTextView      = view.findViewById(R.id.tempMinTextView)  ;
        tempMaxTextView      = view.findViewById(R.id.tempMaxTextView) ;
        windDirectionTextView= view.findViewById(R.id.windDirectionTextView) ;


        firstHourTextView  = view.findViewById(R.id.firstHourTextView);
        secondHourTextView = view.findViewById(R.id.secondHourTextView);
        thirdHourTextView  = view.findViewById(R.id.thirdHourTextView);
        fourthHourTextView = view.findViewById(R.id.fourthHourTextView);
        fifthHourTextView  = view.findViewById(R.id.fifthHourTextView);
        sixthHourTextView  = view.findViewById(R.id.sixthHourTextView);
        seventhHourTextView= view.findViewById(R.id.seventhHourTextView);

        firstHourImage  = view.findViewById(R.id.firstHourImage);
        secondHourImage = view.findViewById(R.id.secondHourImage);
        thirdHourImage  = view.findViewById(R.id.thirdHourImage);
        fourthHourImage = view.findViewById(R.id.fourthHourImage);
        fifthHourImage  = view.findViewById(R.id.fifthHourImage);
        sixthHourImage  = view.findViewById(R.id.sixthHourImage);
        seventhHourImage= view.findViewById(R.id.seventhHourImage);


        firstHourTemp  = view.findViewById(R.id.firstHourTemp);
        secondHourTemp = view.findViewById(R.id.secondHourTemp);
        thirdHourTemp  = view.findViewById(R.id.thirdHourTemp);
        fourthHourTemp = view.findViewById(R.id.fourthHourTemp);
        fifthHourTemp  = view.findViewById(R.id.fifthHourTemp);
        sixthHourTemp  = view.findViewById(R.id.sixthHourTemp);
        seventhHourTemp= view.findViewById(R.id.seventhHourTemp);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        getCurrentTime();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    getLocation();
                    getCurrentWeatherByCoordinates();
                    // getCurrentWeatherByCityName(editText.getText().toString().trim());


                } else {
                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

        getCurrentWeatherByCoordinates();

        return view;
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();

                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        latitude = String.valueOf(addresses.get(0).getLatitude());
                        longitude = String.valueOf(addresses.get(0).getLongitude());

                        location_text.setText(latitude);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }


    void getCurrentWeatherByCoordinates() {
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

                Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();


                if (response.code() == 200) {
                    WeatherResult weatherResponse = response.body();

                    assert weatherResponse != null;




                    cityNameTextView.setText(weatherResponse.getCityObject().getName());
                    currentTemperatureTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp())) + "°C");
                    currentWeatherTextView.setText(weatherResponse.getList().get(0).getWeatherList().get(0).getMain());

                    setBackgroundImage(weatherResponse.getList().get(0).getWeatherList().get(0).getMain());






                    setImage(todayImageWeather, weatherResponse.getList().get(0).getWeatherList().get(0).getIcon());
                    setImage(secondImageView, weatherResponse.getList().get(8).getWeatherList().get(0).getIcon());
                    setImage(thirdImageView, weatherResponse.getList().get(16).getWeatherList().get(0).getIcon());
                    setImage(fourthImageView, weatherResponse.getList().get(24).getWeatherList().get(0).getIcon());
                    setImage(fifthImageView, weatherResponse.getList().get(32).getWeatherList().get(0).getIcon());
                    setImage(sixthImageView, weatherResponse.getList().get(39).getWeatherList().get(0).getIcon());



                    todayTempTextView.setText(String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp_max())) + "°");
                    secondTemp.setText(String.valueOf(Math.round(weatherResponse.getList().get(8).getMainList().getTemp_max())) + "°");
                    thirdTemp.setText(String.valueOf(Math.round(weatherResponse.getList().get(16).getMainList().getTemp_max())) + "°");
                    fourthTemp.setText(String.valueOf(Math.round(weatherResponse.getList().get(24).getMainList().getTemp_max())) + "°");
                    fifthTemp.setText(String.valueOf(Math.round(weatherResponse.getList().get(32).getMainList().getTemp_max())) + "°");
                    sixthTemp.setText(String.valueOf(Math.round(weatherResponse.getList().get(39).getMainList().getTemp_max())) + "°");


                    firstDateTxtView.setText(getDay(weatherResponse.getList().get(0).getDt_txt()));
                    secondDateTxtView.setText(getDay(weatherResponse.getList().get(8).getDt_txt()));
                    thirdDateTxtView.setText(getDay(weatherResponse.getList().get(16).getDt_txt()));
                    fouthDateTxtView.setText(getDay(weatherResponse.getList().get(24).getDt_txt()));
                    fifthDateTxtView.setText(getDay(weatherResponse.getList().get(32).getDt_txt()));
                    sixthDateTxtView.setText(getDay(weatherResponse.getList().get(39).getDt_txt()));


                    windSpeedTextView    .setText("Wind speed\n"+String.valueOf(+Math.round(weatherResponse.getList().get(0).getWindList().getSpeed()))+"km/h" );
                    feelsLikeTextView    .setText("Feels like\n"+String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getFeels_like()))+"°");
                    humidityTextView     .setText("Humidity\n"+String.valueOf(weatherResponse.getList().get(0).getMainList().getHumidity())+"%");
                    visibilityTextView   .setText("Visibility\n"+String.valueOf(weatherResponse.getList().get(0).getVisibility()/1000)+"km");
                    pressureTextView     .setText("Pressure\n"+String.valueOf(weatherResponse.getList().get(0).getMainList().getPressure())+"hPa");
                    tempMinTextView      .setText("Min temperature\n"+String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp_min()))+"°");
                    tempMaxTextView      .setText("Max temperature\n"+String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp_max()))+"°");
                    windDirectionTextView.setText("Wind direction\n"+String.valueOf(weatherResponse.getList().get(0).getWindList().getDeg()));

                    firstHourTextView  .setText(getHour(weatherResponse.getList().get(0).getDt_txt()));
                    secondHourTextView .setText(getHour(weatherResponse.getList().get(1).getDt_txt()));
                    thirdHourTextView  .setText(getHour(weatherResponse.getList().get(2).getDt_txt()));
                    fourthHourTextView .setText(getHour(weatherResponse.getList().get(3).getDt_txt()));
                    fifthHourTextView  .setText(getHour(weatherResponse.getList().get(4).getDt_txt()));
                    sixthHourTextView  .setText(getHour(weatherResponse.getList().get(5).getDt_txt()));
                    seventhHourTextView.setText(getHour(weatherResponse.getList().get(6).getDt_txt()));


                    setImage(firstHourImage  , weatherResponse.getList().get(0).getWeatherList().get(0).getIcon());
                    setImage(secondHourImage , weatherResponse.getList().get(1).getWeatherList().get(0).getIcon());
                    setImage(thirdHourImage  , weatherResponse.getList().get(2).getWeatherList().get(0).getIcon());
                    setImage(fourthHourImage , weatherResponse.getList().get(3).getWeatherList().get(0).getIcon());
                    setImage(fifthHourImage  , weatherResponse.getList().get(4).getWeatherList().get(0).getIcon());
                    setImage(sixthHourImage  , weatherResponse.getList().get(5).getWeatherList().get(0).getIcon());
                    setImage(seventhHourImage, weatherResponse.getList().get(6).getWeatherList().get(0).getIcon());



                    firstHourTemp  .setText(String.valueOf(Math.round(weatherResponse.getList().get(0).getMainList().getTemp())) + "°");
                    secondHourTemp .setText(String.valueOf(Math.round(weatherResponse.getList().get(1).getMainList().getTemp())) + "°");
                    thirdHourTemp  .setText(String.valueOf(Math.round(weatherResponse.getList().get(2).getMainList().getTemp())) + "°");
                    fourthHourTemp .setText(String.valueOf(Math.round(weatherResponse.getList().get(3).getMainList().getTemp())) + "°");
                    fifthHourTemp  .setText(String.valueOf(Math.round(weatherResponse.getList().get(4).getMainList().getTemp())) + "°");
                    sixthHourTemp  .setText(String.valueOf(Math.round(weatherResponse.getList().get(5).getMainList().getTemp())) + "°");
                    seventhHourTemp.setText(String.valueOf(Math.round(weatherResponse.getList().get(6).getMainList().getTemp())) + "°");

                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {


                Log.d("Response", t.getMessage());
                Toast.makeText(getContext(), "Request failed: ", Toast.LENGTH_SHORT).show();

            }
        });
    }































    void getCurrentWeatherByCityName(String cityName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);


        //Call<WeatherResult> call = service.getCurrentWeather(latitude,longitude, APIManager.API_ID);
        Call<WeatherResult> call = service.getCurrentWeatherByCityName(cityName, APIManager.API_ID, APIManager.UNITS);


        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                Log.d("Response", String.valueOf(response.code()));

                Toast.makeText(getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();


                if (response.code() == 200) {
                    WeatherResult weatherResponse = response.body();

                    assert weatherResponse != null;

                    String stringBuilder =
                            "Country: " + weatherResponse.getCityObject().getName();


                    cityNameTextView.setText(weatherResponse.getCityObject().getName());
                    currentTemperatureTextView.setText(String.valueOf(weatherResponse.getList().get(0).getMainList().getTemp()) + "°C");
                    currentWeatherTextView.setText(weatherResponse.getList().get(0).getWeatherList().get(0).getMain());




                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {


                Log.d("Response", t.getMessage());
                Toast.makeText(getContext(), "Request failed: ", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void getCurrentTime() {

        Date dates = Calendar.getInstance().getTime();
        DateFormat dateFormat1 = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        DateFormat dateFormat2 = new SimpleDateFormat("MMM dd",  Locale.ENGLISH);
        DateFormat dateFormat3 = new SimpleDateFormat("HH:mm",  Locale.ENGLISH);


        String strDate1 = dateFormat1.format(dates).substring(0, 1).toUpperCase() + dateFormat1.format(dates).substring(1) + " |";
        String strDate2 = dateFormat2.format(dates).substring(0, 1).toUpperCase() + dateFormat2.format(dates).substring(1) + " |";
        String strDate3 = dateFormat3.format(dates);



        dayTextView.setText(strDate1);
        monthTextView.setText(strDate2);
        timeTextView.setText(strDate3);
    }



    private void setBackgroundImage(String main) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (main) {
                    case "Clouds":
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.cloudy));
                        break;
                    case "Drizzle":
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.cloudy));
                        break;
                    case "Clear":
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.u));
                        break;
                    case "Thunderstorm":
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.stormy));
                        break;
                    case "Rain":
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.stormy));
                        break;
                    case "Snow":
                        linearLayout.setBackground(getResources().getDrawable(R.drawable.chilly));
                        break;

                }
            }
        });
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

    private  String getDay(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
        Date myDate = null;
        try {
            myDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("EEE");
        String sMyDate = sdf.format(myDate);

        return sMyDate;


    }

    private  String getHour(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
        Date myDate = null;
        try {
            myDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("HH:mm");
        String sMyDate = sdf.format(myDate);

        return sMyDate;


    }
}