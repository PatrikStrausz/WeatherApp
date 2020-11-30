package com.example.weatherapp.mylocation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.mylocation.LocationViewHolder;
import com.example.weatherapp.weather.WeatherResult;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {

    private Context context;



    private List<WeatherResult> cachedWeather;

    public void setCachedWeather(List<WeatherResult> cachedWeather) {
        this.cachedWeather = cachedWeather;
        notifyDataSetChanged();
    }



    public LocationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_my_locations_list, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        if(cachedWeather != null) {
            holder.bind(cachedWeather.get(position), context);

        }

    }

    @Override
    public int getItemCount() {
        if (cachedWeather == null) {
            return 0;
        }
        return cachedWeather.size();

    }



}



