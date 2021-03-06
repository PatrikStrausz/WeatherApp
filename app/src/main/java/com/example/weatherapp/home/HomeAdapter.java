package com.example.weatherapp.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;

import com.example.weatherapp.weather.WeatherResult;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private Context context;

    private WeatherResult mAllRepositories;


    public void setmAllRepositories(WeatherResult mAllRepositories) {
        this.mAllRepositories = mAllRepositories;
        notifyDataSetChanged();
    }


    public HomeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_home_list, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(mAllRepositories, context);


    }

    @Override
    public int getItemCount() {
        if (mAllRepositories == null) {
            return 0;
        } else {
            return 1;
        }
    }


}



