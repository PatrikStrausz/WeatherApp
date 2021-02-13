package com.example.weatherapp;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.weatherapp.converters.Converters;
import com.example.weatherapp.weather.City;
import com.example.weatherapp.weather.WeatherResult;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherViewModel extends AndroidViewModel {


    private WeatherRepository repository;
    private LiveData<WeatherResult> mRepos;

    private LiveData<WeatherResult> location;

    private LiveData<List<WeatherResult>> weatherList;

    public WeatherViewModel(Application application){
        super(application);

        repository = new WeatherRepository(application);
        weatherList = repository.getWeatherList();

        mRepos = repository.getRepos();

        location= repository.getLocation();
    }


    public LiveData<List<WeatherResult>> getWeatherList() {

        return weatherList;
    }

    public void insert(WeatherResult weatherResult){
        repository.insert(weatherResult);
    }



    public void delete(WeatherResult weatherResult){
        repository.delete(weatherResult);
    }

    public LiveData<WeatherResult> getLocation() {
        return location;
    }

    public LiveData<WeatherResult> getRepos() {
        return mRepos;
    }

    public void getWeatherByCoordinates(LatLng coordinates) {
        repository.getWeatherByCoordinates(coordinates);
    }

    public void getWeatherByCityName(String city) {
         repository.getWeatherByCityName(city);
    }

    @Dao
    public static interface WeatherDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(WeatherResult weatherResult);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertCity(City city);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertResultAndCity(WeatherResult weatherResult,City city);

        @Delete
        void deleteResultAndCity(WeatherResult weatherResult,City city);

        @Query("SELECT * from weather_result ")
        LiveData<List<WeatherResult>> getAllWeatherResults();


        @Query("DELETE FROM weather_result")
        void deleteAll();





    }

    @Database(entities = {WeatherResult.class, City.class}, version = 1, exportSchema = false)
    @TypeConverters({Converters.class})
    public abstract static class WeatherDatabase extends RoomDatabase {

        public abstract WeatherDao weatherDao();

        private static Context contexts;

        private static volatile WeatherDatabase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static WeatherDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (WeatherDatabase.class) {
                    if (INSTANCE == null) {
                        contexts = context;
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                WeatherDatabase.class, "weather_database")
                                .addCallback(callback)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }

        private  static Callback callback = new Callback(){
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);

                databaseWriteExecutor.execute(()->{
                    WeatherDao weatherDao = INSTANCE.weatherDao();
    //                weatherDao.deleteAll();





                });
            }
        };
    }
}
