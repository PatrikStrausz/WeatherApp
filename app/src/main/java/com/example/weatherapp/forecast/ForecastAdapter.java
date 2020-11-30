package com.example.weatherapp.forecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.OnForecastClick;
import com.example.weatherapp.R;
import com.example.weatherapp.weather.WeatherResult;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private Context context;

    private WeatherResult mAllRepositories;

    private OnForecastClick listener;

    public void setmAllRepositories(WeatherResult mAllRepositories) {
        this.mAllRepositories = mAllRepositories;
        notifyDataSetChanged();
    }

    public void setListener(OnForecastClick listener) {
        this.listener = listener;
    }

    public ForecastAdapter(Context context) {
        this.context = context;
    }



    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.fragment_forecast_list, parent, false);
        ForecastViewHolder holder = new ForecastViewHolder(view);
        holder.setListener(listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
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
