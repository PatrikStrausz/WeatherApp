package com.example.weatherapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.lifecycle.Observer;

import com.example.weatherapp.R;
import com.example.weatherapp.WeatherImages;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.weather.WeatherResult;

import java.util.Random;

public class WidgetService extends JobIntentService {

    private static final int JOB_ID = 8;
    public static final String INTENT_KEY_LOCATION = "loc";

private WeatherImages weatherImages = new WeatherImages();

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, WidgetService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        WeatherResult weatherResult = (WeatherResult) intent.getSerializableExtra(INTENT_KEY_LOCATION);


        Log.d("SERVICE", ">>>>>>>>>>>" + weatherResult.getCityObject().getName());
        AppWidgetManager appWidgetManager =
                AppWidgetManager.getInstance(this
                .getApplicationContext());

        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getApplication(),WidgetProvider.class));




        for (int widgetId : allWidgetIds) {


            RemoteViews remoteViews = new RemoteViews(this
                    .getApplicationContext().getPackageName(),
                    R.layout.widget_layout);

        remoteViews.setTextViewText(R.id.widget_temp,
                Math.round(weatherResult.getList().get(0).getMainList().getTemp())+"°");
        remoteViews.setTextViewText(R.id.widget_city,
                weatherResult.getCityObject().getName());


            Intent clickIntent = new Intent(this.getApplicationContext(),
                    WidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                    allWidgetIds);


            SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("city",weatherResult.getCityObject().getName());
            editor.putString("temp",Math.round(weatherResult.getList().get(0).getMainList().getTemp())+"°");
            editor.apply();

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();
    }
}
