package com.example.weatherapp.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.weatherapp.BuildConfig;
import com.example.weatherapp.R;

import com.example.weatherapp.weather.WeatherResult;
import com.example.weatherapp.WeatherViewModel;
import com.example.weatherapp.widget.WidgetService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;


public class HomeFragment extends Fragment {

    private Location location;
    private FusedLocationProviderClient fusedLocationProviderClient;


    private WeatherViewModel weatherViewModel;

    private SwipeRefreshLayout swipeRefreshLayout;

    private NotificationManagerCompat notificationManagerCompat;


    private static final int PERMISSION_CODE = 33;
    private static final String PERM = Manifest.permission.ACCESS_FINE_LOCATION;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        swipeRefreshLayout = view.findViewById(R.id.linearLayout);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());


        RecyclerView mRepositoryRecyclerView = view.findViewById(R.id.recycler_view);
        HomeAdapter homeAdapter = new HomeAdapter(getContext());
        mRepositoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRepositoryRecyclerView.setAdapter(homeAdapter);

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);


        weatherViewModel.getRepos().observe(requireActivity(), new Observer<WeatherResult>() {
            @Override
            public void onChanged(WeatherResult weatherResult) {


                homeAdapter.setmAllRepositories(weatherResult);


                Intent intent = new Intent();
                intent.putExtra(WidgetService.INTENT_KEY_LOCATION, weatherResult);
                WidgetService.enqueueWork(getContext(), intent);

            }
        });


        if (checkPermissions()) {
            getLastLocation();

        } else {
            requestPermissions();


        }


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                getLastLocation();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions() {
        requestPermissions(new String[]{PERM}, PERMISSION_CODE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkPermissions() {

        return requireActivity().checkSelfPermission(PERM) == PackageManager.PERMISSION_GRANTED;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                getLastLocation();


            } else {

                Snackbar.make(getActivity().findViewById(android.R.id.content), "permissions denied",
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
                    location = locationTask.getResult();



                    updateLocation();


                }

            }


        });


    }


    private void updateLocation() {

        if (location == null) {
            LatLng defaultLocation = new LatLng(40.71395, 21.25808);
            weatherViewModel.getWeatherByCoordinates(defaultLocation);

        } else {

            weatherViewModel.getWeatherByCoordinates(new LatLng(location.getLatitude(), location.getLongitude()));
        }


    }


}