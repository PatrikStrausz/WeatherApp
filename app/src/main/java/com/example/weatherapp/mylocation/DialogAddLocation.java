package com.example.weatherapp.mylocation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.R;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.mylocation.LocationAdapter;
import com.example.weatherapp.weather.WeatherResult;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.CountDownLatch;

public class DialogAddLocation extends DialogFragment {

    FragmentActivity activity;
    public DialogAddLocation(FragmentActivity fragmentActivity) {
        activity = fragmentActivity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);

       ViewModelProvider provider = new ViewModelProvider(requireActivity());
        WeatherViewModel weatherViewModel = provider.get(WeatherViewModel.class);



        TextInputEditText editText = view.findViewById(R.id.edit_text);

        builder.setMessage("Insert city to add")
                .setView(view)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                                weatherViewModel.getWeatherByCityName(editText.getText().toString().trim());


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });





        return builder.create();
    }
}
