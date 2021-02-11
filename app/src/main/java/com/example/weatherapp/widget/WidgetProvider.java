package com.example.weatherapp.widget;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.converters.DateFormatter;
import com.example.weatherapp.weather.WeatherResult;


public class WidgetProvider extends AppWidgetProvider {




    private DateFormatter dateFormatter = new DateFormatter();




    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            views.setOnClickPendingIntent(R.id.widget_linear, pendingIntent);


            SharedPreferences prefs = context.getSharedPreferences("prefs",Context.MODE_PRIVATE);

            Log.d("SERVICE", "onEnabled: " +prefs.getString("temp","sasasas"));
            views.setTextViewText(R.id.widget_city, prefs.getString("city","city name"));
            views.setTextViewText(R.id.widget_temp, prefs.getString("temp", "temp "));
            views.setTextViewText(R.id.widget_date, dateFormatter.getWidgetDate());




            appWidgetManager.updateAppWidget(appWidgetId, views);
            Log.d("SERVICE", "onUpdate: ");








        }



    }




}


