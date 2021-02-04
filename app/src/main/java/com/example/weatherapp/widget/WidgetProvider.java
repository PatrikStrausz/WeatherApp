package com.example.weatherapp.widget;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import android.widget.RemoteViews;


import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.converters.DateFormatter;


public class WidgetProvider extends AppWidgetProvider {


    private DateFormatter dateFormatter = new DateFormatter();

    private WeatherViewModel weatherViewModel;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            views.setOnClickPendingIntent(R.id.widget_linear, pendingIntent);


            views.setTextViewText(R.id.dateTextView, dateFormatter.getWidgetDate());


            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


}
