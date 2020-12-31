package com.example.weatherapp.forecast;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.OnForecastClick;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.weather.WeatherResult;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastViewHolder> {

    private Context context;

    private WeatherResult mAllRepositories;


    private List<WeatherResult> weatherResults = new ArrayList<>();




    private OnForecastClick listener;



    public void setmAllRepositories(WeatherResult mAllRepositories) {
        this.mAllRepositories = mAllRepositories;
        weatherResults.add(mAllRepositories);
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
        WeatherResult weatherResult = weatherResults.get(position);
        Log.d("TAG", "s "+weatherResult.getList().get(position).getMainList().getHumidity());
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount: "+weatherResults.size());
     return   weatherResults.size();
    }
}
