package com.example.weatherapp.mylocation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.R;
import com.example.weatherapp.mylocation.detail.fragments.NextFiveDaysFragment;
import com.example.weatherapp.mylocation.detail.fragments.TodayFragment;
import com.example.weatherapp.weather.WeatherResult;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyLocationDetail extends AppCompatActivity implements Serializable {



    private WeatherResult weatherResult;

    private int index;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private TodayFragment todayFragment;
    private NextFiveDaysFragment nextFiveDaysFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_layout);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        weatherResult = (WeatherResult) extras.getSerializable("Response");

        index = extras.getInt("Index", 0);


        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        todayFragment = new TodayFragment(weatherResult, index);
        nextFiveDaysFragment = new NextFiveDaysFragment(weatherResult);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(nextFiveDaysFragment,"Next 5 days");
        viewPagerAdapter.addFragment(todayFragment,"Today");
        viewPager.setAdapter(viewPagerAdapter);
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {


        private List<Fragment> fragmentList = new ArrayList<>();

        private List<String> titles = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }


        public void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            titles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
