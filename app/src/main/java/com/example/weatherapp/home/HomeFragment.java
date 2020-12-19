package com.example.weatherapp.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;
import com.example.weatherapp.weather.WeatherResult;
import com.example.weatherapp.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment  {

    private Location location;

    private WeatherViewModel weatherViewModel;


    private static final int PERMISSION_CODE = 33;
    private static final String PERM = Manifest.permission.ACCESS_FINE_LOCATION;


    private FusedLocationProviderClient fusedLocationProviderClient;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);




        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());


        RecyclerView mRepositoryRecyclerView = view.findViewById(R.id.recycler_view);
        HomeAdapter homeAdapter = new HomeAdapter(getContext());
        mRepositoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRepositoryRecyclerView.setAdapter(homeAdapter);

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);



        weatherViewModel.getRepos().observe(requireActivity(), new Observer<WeatherResult>() {


            @Override
            public void onChanged(WeatherResult weatherResult) {
                if(weatherResult != null){

                    homeAdapter.setmAllRepositories(weatherResult);


                }else{
                    Log.d("Response", "null");
                }

            }
        });




                if (checkPermissions()) {
                    getLastLocation();
                    updateMap();
                } else {
                    requestPermissions();
                    updateMap();
                }



        return view;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{PERM}, PERMISSION_CODE);
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(getContext(), PERM) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLastLocation();
            } else {

                Snackbar.make(getView().findViewById(android.R.id.content), "permissions denied",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("Go to settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();

            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    location = task.getResult();
                    updateMap();


                }
            }
        });
    }



    private void updateMap() {

        if (location == null) {

            LatLng kosice = new LatLng(48.71395, 21.25808);
            weatherViewModel.getWeatherByCoordinates(kosice);
        } else {


            weatherViewModel.getWeatherByCoordinates(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }





}