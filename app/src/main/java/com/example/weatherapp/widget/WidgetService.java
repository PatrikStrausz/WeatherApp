package com.example.weatherapp.widget;

import android.app.Notification;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.converters.WeatherImages;
import com.example.weatherapp.weather.WeatherResult;

import static com.example.weatherapp.notification.WeatherNotification.CHANNEL_1_ID;

public class WidgetService extends JobIntentService {

    private static final int JOB_ID = 8;
    public static final String INTENT_KEY_LOCATION = "loc";

private WeatherImages weatherImages = new WeatherImages();

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, WidgetService.class, JOB_ID, work);
    }

    private NotificationManagerCompat notificationManager;

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        WeatherResult weatherResult = (WeatherResult) intent.getSerializableExtra(INTENT_KEY_LOCATION);

        notificationManager = NotificationManagerCompat.from(this);

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
        sendOnChannel1(weatherResult.getCityObject().getName(),
                Math.round(weatherResult.getList().get(0).getMainList().getTemp())+"°",
                Math.round(weatherResult.getList().get(0).getMainList().getTemp_min())+"°"
                );

        stopSelf();
    }

    public void sendOnChannel1(String title, String temp,String temp2) {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_cloud)
                .setContentTitle(title +" "+temp+"/"+ temp2)
                .setContentText("Click to learn more")
                .setColorized(true)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .build();




        notificationManager.notify(1, notification);
    }
}
