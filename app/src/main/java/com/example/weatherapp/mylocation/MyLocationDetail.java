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

import com.example.weatherapp.converters.DateFormatter;
import com.example.weatherapp.R;
import com.example.weatherapp.mylocation.detail.fragments.FifthDayFragment;
import com.example.weatherapp.mylocation.detail.fragments.FourthDayFragment;
import com.example.weatherapp.mylocation.detail.fragments.SecondDayFragment;
import com.example.weatherapp.mylocation.detail.fragments.SixthDayFragment;
import com.example.weatherapp.mylocation.detail.fragments.ThirdDayFragment;
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
    private SecondDayFragment secondDayFragment;
    private ThirdDayFragment thirdDayFragment;
    private FourthDayFragment fourthDayFragment;
    private FifthDayFragment fifthDayFragment;
    private SixthDayFragment sixthDayFragment;


    private DateFormatter dateFormatter = new DateFormatter();



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
        secondDayFragment = new SecondDayFragment(weatherResult,index+8);
        thirdDayFragment = new ThirdDayFragment(weatherResult,index+16);
        fourthDayFragment = new FourthDayFragment(weatherResult,index+24);
        fifthDayFragment = new FifthDayFragment(weatherResult,index+32);
        sixthDayFragment = new SixthDayFragment(weatherResult,index+33);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(todayFragment,"Today");
        viewPagerAdapter.addFragment(secondDayFragment,dateFormatter.getDay(weatherResult.getList().get(8).getDt_txt()));
        viewPagerAdapter.addFragment(thirdDayFragment,dateFormatter.getDay(weatherResult.getList().get(16).getDt_txt()));
        viewPagerAdapter.addFragment(fourthDayFragment,dateFormatter.getDay(weatherResult.getList().get(24).getDt_txt()));
        viewPagerAdapter.addFragment(fifthDayFragment,dateFormatter.getDay(weatherResult.getList().get(32).getDt_txt()));
        viewPagerAdapter.addFragment(sixthDayFragment,dateFormatter.getDay(weatherResult.getList().get(39).getDt_txt()));


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
