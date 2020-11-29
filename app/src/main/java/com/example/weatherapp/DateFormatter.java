package com.example.weatherapp;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    public String getDay(String date) {
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

    public String getHour(String date) {
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

    public String getCurrentTime(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
        Date myDate = null;
        try {
            myDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("EEE dd/MM");
        String sMyDate = sdf.format(myDate);
        sMyDate = sMyDate.replaceAll(" ", "\n");

        return sMyDate;


    }

    public void getCurrentTime(TextView dayTextView, TextView monthTextView, TextView timeTextView) {

        Date dates = Calendar.getInstance().getTime();
        DateFormat dateFormat1 = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        DateFormat dateFormat2 = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
        DateFormat dateFormat3 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);


        String strDate1 = dateFormat1.format(dates).substring(0, 1).toUpperCase() + dateFormat1.format(dates).substring(1) + " |";
        String strDate2 = dateFormat2.format(dates).substring(0, 1).toUpperCase() + dateFormat2.format(dates).substring(1) + " |";
        String strDate3 = dateFormat3.format(dates);


        dayTextView.setText(strDate1);
        monthTextView.setText(strDate2);
        timeTextView.setText(strDate3);
    }

}
