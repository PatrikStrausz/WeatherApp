package com.example.weatherapp.mylocation;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.CustomForecastClick;
import com.example.weatherapp.OnForecastClick;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.mylocation.LocationViewHolder;
import com.example.weatherapp.weather.WeatherResult;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {

    private final Activity context;

    private CustomForecastClick listener;

    private WeatherResult mRecentlyDeletedItem;
    private int mRecentlyDeletedItemPosition;

    private final WeatherViewModel weatherViewModel;



    public void setListener(CustomForecastClick listener) {
        this.listener = listener;
    }

    private List<WeatherResult> cachedWeather = new ArrayList<>();

    public void setCachedWeather(List<WeatherResult> cachedWeather) {

        this.cachedWeather = cachedWeather;
        notifyDataSetChanged();
    }


    public LocationAdapter(Activity context, WeatherViewModel weatherViewModel) {
        this.context = context;
        this.weatherViewModel = weatherViewModel;

    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_my_locations_list, parent, false);
        LocationViewHolder holder = new LocationViewHolder(view);
        holder.setListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        if(cachedWeather != null) {
            holder.bind(cachedWeather.get(position), context);

        }

    }


    @Override
    public int getItemCount() {

        int limit = 10;
        return Math.min(cachedWeather.size(), limit);

    }



    public void deleteItem(int position) {
        mRecentlyDeletedItem = cachedWeather.get(position);
        mRecentlyDeletedItemPosition = position;
        weatherViewModel.delete(cachedWeather.get(position));
        cachedWeather.remove(position);

        notifyItemRemoved(position);
        showUndoSnackbar();
    }

    private void showUndoSnackbar() {

        View view = context.findViewById(R.id.coordinator_layout);
        Snackbar snackbar = Snackbar.make(view, "City has been deleted",
                Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", v -> undoDelete());
        snackbar.show();
    }

    private void undoDelete() {
        cachedWeather.add(mRecentlyDeletedItemPosition,
                mRecentlyDeletedItem);
        weatherViewModel.insert(mRecentlyDeletedItem);
        notifyItemInserted(mRecentlyDeletedItemPosition);
    }

    public Activity getContext() {
        return context;
    }


}



