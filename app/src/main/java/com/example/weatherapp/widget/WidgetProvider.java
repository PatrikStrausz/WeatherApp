package com.example.weatherapp.widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.converters.DateFormatter;
import com.example.weatherapp.home.HomeFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class WidgetProvider extends AppWidgetProvider {


private DateFormatter dateFormatter = new DateFormatter();




    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId: appWidgetIds){
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            views.setOnClickPendingIntent(R.id.widget_linear, pendingIntent);





            views.setTextViewText(R.id.dateTextView, dateFormatter.getWidgetDate() );


            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }








}
