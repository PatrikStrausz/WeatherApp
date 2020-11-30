package com.example.weatherapp.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
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

import com.example.weatherapp.R;
import com.example.weatherapp.weather.WeatherResult;
import com.example.weatherapp.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment  {

    private FusedLocationProviderClient fusedLocationClient;



    private String latitude;
    private String longitude;

    private TextView location_text;
    private TextView cityNameTextView;




    private WeatherViewModel weatherViewModel;
    private WeatherResult weatherResponse;
    private RecyclerView mRepositoryRecyclerView;


    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);





        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        mRepositoryRecyclerView = view.findViewById(R.id.recycler_view);
        HomeAdapter homeAdapter = new HomeAdapter(getContext());
        mRepositoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRepositoryRecyclerView.setAdapter(homeAdapter);

        weatherViewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        weatherViewModel.getRepos().observe(requireActivity(), new Observer<WeatherResult>() {


            @Override
            public void onChanged(WeatherResult weatherResult) {
                if(weatherResult != null){
                    weatherResponse = weatherResult;
                    homeAdapter.setmAllRepositories(weatherResult);


                    Log.d("Response", "onChanged: "+weatherResponse.getCityObject().getName());
                }else{
                    Log.d("Response", "null");
                }

            }
        });

        weatherViewModel.getWeatherByCoordinates();



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ActivityCompat.checkSelfPermission(requireContext(),
//                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//
//                    getLocation();
////                    getCurrentWeatherByCityName(editText.getText().toString().trim());
//
//
//                } else {
//                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
//                }
//            }
//        });


        return view;
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();

                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        latitude = String.valueOf(addresses.get(0).getLatitude());
                        longitude = String.valueOf(addresses.get(0).getLongitude());

                        location_text.setText(latitude);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }



}