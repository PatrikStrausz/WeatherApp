package com.example.weatherapp;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface APIManager {


  public static final String API_ID = "c7d09582920dcd4fbe1d32e6c04bfbe8";
  public static final String API_URL = "https://api.openweathermap.org/data/2.5/";
  public static final String UNITS = "metric";



  @GET("forecast?")
  Call<WeatherResult> getCurrentWeather(@Query("lat") String lat,
                                        @Query("lon") String lon,
                                        @Query("appid") String appId,
                                        @Query("units") String units);


  @GET("forecast?")
  Call<WeatherResult> getCurrentWeatherByCityName
          (@Query("q") String cityName,
           @Query("appid") String appId,
           @Query("units") String units);


}