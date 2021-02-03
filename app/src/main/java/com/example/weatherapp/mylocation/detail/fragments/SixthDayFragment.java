package com.example.weatherapp.mylocation.detail.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.weatherapp.DateFormatter;
import com.example.weatherapp.R;
import com.example.weatherapp.weather.WeatherResult;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class SixthDayFragment extends Fragment {


    private TextView humidity;
    private TextView windSpeed;
    private TextView pressure;
    private TextView wind_temp;
    private TextView visibility;
    private TextView feelsLike;

    private TextView firstHour;
    private TextView secondHour;
    private TextView thirdHour;
    private TextView fourthHour;
    private TextView fifthHour;
    private TextView sixthHour;
    private TextView seventhHour;

    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourthImage;
    private ImageView fifthImage;
    private ImageView sixthImage;
    private ImageView seventhImage;


    private TextView firstHours;
    private TextView secondHours;
    private TextView thirdHours;
    private TextView fourthHours;
    private TextView fifthHours;
    private TextView sixthHours;
    private TextView seventhHours;

    private ImageView firstImages;
    private ImageView secondImages;
    private ImageView thirdImages;
    private ImageView fourthImages;
    private ImageView fifthImages;
    private ImageView sixthImages;
    private ImageView seventhImages;

    private WeatherResult weatherResult;

    private int index;

    private LineChart lineChart;
    private LineChart lineChart2;

    private DateFormatter dateFormatter = new DateFormatter();

    public SixthDayFragment(WeatherResult weatherResult, int index) {
        this.weatherResult = weatherResult;
        this.index = index;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_forecast_detail, container, false);


        visibility = view.findViewById(R.id.visibility);
        humidity =  view.findViewById(R.id.humidity);
        feelsLike =  view.findViewById(R.id.feels_like);
        pressure =  view.findViewById(R.id.pressure);
        wind_temp =  view.findViewById(R.id.wind_temp);
        windSpeed =  view.findViewById(R.id.wind_speed);

        firstHour =  view.findViewById(R.id.firstHour);
        secondHour =  view.findViewById(R.id.secondHour);
        thirdHour =  view.findViewById(R.id.thirdHour);
        fourthHour = view. findViewById(R.id.fourthHour);
        fifthHour =  view.findViewById(R.id.fifthHour);
        sixthHour =  view.findViewById(R.id.sixthHour);
        seventhHour =  view.findViewById(R.id.seventhHour);

        firstImage =  view.findViewById(R.id.firstHourImage);
        secondImage =  view.findViewById(R.id.secondHourImage);
        thirdImage = view. findViewById(R.id.thirdHourImage);
        fourthImage =  view.findViewById(R.id.fourthHourImage);
        fifthImage = view. findViewById(R.id.fifthHourImage);
        sixthImage =  view.findViewById(R.id.sixthHourImage);
        seventhImage =  view.findViewById(R.id.seventhHourImage);

        firstHours = view. findViewById(R.id.firstHours);
        secondHours =  view.findViewById(R.id.secondHours);
        thirdHours =  view.findViewById(R.id.thirdHours);
        fourthHours = view. findViewById(R.id.fourthHours);
        fifthHours =  view.findViewById(R.id.fifthHours);
        sixthHours =  view.findViewById(R.id.sixthHours);
        seventhHours =  view.findViewById(R.id.seventhHours);

        firstImages =  view.findViewById(R.id.firstHourImages);
        secondImages =  view.findViewById(R.id.secondHourImages);
        thirdImages =  view.findViewById(R.id.thirdHourImages);
        fourthImages =  view.findViewById(R.id.fourthHourImages);
        fifthImages =  view.findViewById(R.id.fifthHourImages);
        sixthImages =  view.findViewById(R.id.sixthHourImages);
        seventhImages =  view.findViewById(R.id.seventhHourImages);

        lineChart =  view.findViewById(R.id.lineChart);
        lineChart2 =  view.findViewById(R.id.lineChart2);


        assignValues();

        setupChart();
        setupChart2();



        return view;
    }



    private ArrayList<Entry> lineChartData() {

        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(0, Math.round(weatherResult.getList().get(index).getMainList().getTemp())));
        data.add(new Entry(1, Math.round(weatherResult.getList().get(index + 1).getMainList().getTemp())));
        data.add(new Entry(2, Math.round(weatherResult.getList().get(index + 2).getMainList().getTemp())));
        data.add(new Entry(3, Math.round(weatherResult.getList().get(index + 3).getMainList().getTemp())));
        data.add(new Entry(4, Math.round(weatherResult.getList().get(index + 4).getMainList().getTemp())));
        data.add(new Entry(5, Math.round(weatherResult.getList().get(index + 5).getMainList().getTemp())));
        data.add(new Entry(6, Math.round(weatherResult.getList().get(index + 6).getMainList().getTemp())));
        return data;
    }

    private void setupChart() {
        LineDataSet lineDataSet = new LineDataSet(lineChartData(), "Temp");

        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(15);
        lineDataSet.setValueTextColor(Color.WHITE);

        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) value) + "°";
            }
        });

        ArrayList<ILineDataSet> dataSet = new ArrayList<>();
        dataSet.add(lineDataSet);


        LineData lineData = new LineData(dataSet);

        lineChart.setData(lineData);

        lineChart.setTouchEnabled(false);
        lineChart.setScaleEnabled(false);

        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.getXAxis().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getDescription().setEnabled(false);


        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    private void setupChart2() {
        LineDataSet lineDataSet = new LineDataSet(lineChartData2(), "Humidity");

        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(15);
        lineDataSet.setValueTextColor(Color.WHITE);

        lineDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return ((int) value) + "%";
            }
        });

        ArrayList<ILineDataSet> dataSet = new ArrayList<>();
        dataSet.add(lineDataSet);


        LineData lineData = new LineData(dataSet);

        lineChart2.setData(lineData);

        lineChart2.setTouchEnabled(false);
        lineChart2.setScaleEnabled(false);

        lineChart2.setDrawGridBackground(false);
        lineChart2.setDrawBorders(false);
        lineChart2.getLegend().setEnabled(false);
        lineChart2.getXAxis().setEnabled(false);
        lineChart2.getAxisRight().setEnabled(false);
        lineChart2.getAxisLeft().setEnabled(false);
        lineChart2.getDescription().setEnabled(false);


        lineChart2.notifyDataSetChanged();
        lineChart2.invalidate();
    }

    private List<Entry> lineChartData2() {
        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(0, Math.round(weatherResult.getList().get(index).getMainList().getHumidity())));
        data.add(new Entry(1, Math.round(weatherResult.getList().get(index + 1).getMainList().getHumidity())));
        data.add(new Entry(2, Math.round(weatherResult.getList().get(index + 2).getMainList().getHumidity())));
        data.add(new Entry(3, Math.round(weatherResult.getList().get(index + 3).getMainList().getHumidity())));
        data.add(new Entry(4, Math.round(weatherResult.getList().get(index + 4).getMainList().getHumidity())));
        data.add(new Entry(5, Math.round(weatherResult.getList().get(index + 5).getMainList().getHumidity())));
        data.add(new Entry(6, Math.round(weatherResult.getList().get(index + 6).getMainList().getHumidity())));
        return data;
    }

    private void assignValues() {

        if (weatherResult != null) {
//            visibility.setText(String.valueOf(weatherResult.getList().get(index).getVisibility() / 1000) + "km");
            windSpeed.setText(String.valueOf(+Math.round(weatherResult.getList().get(index).getWindList().getSpeed())) + "km/h");
            feelsLike.setText(String.valueOf(Math.round(weatherResult.getList().get(index).getMainList().getFeels_like())) + "°");
            humidity.setText(String.valueOf(weatherResult.getList().get(index).getMainList().getHumidity()) + "%");
            pressure.setText(String.valueOf(weatherResult.getList().get(index).getMainList().getPressure()) + "hPa");
            wind_temp.setText(String.valueOf(Math.round(weatherResult.getList().get(index).getWindList().getDeg())) + "°");


            firstHour.setText(dateFormatter.getHour(weatherResult.getList().get(index).getDt_txt()));
            secondHour.setText(dateFormatter.getHour(weatherResult.getList().get(index + 1).getDt_txt()));
            thirdHour.setText(dateFormatter.getHour(weatherResult.getList().get(index + 2).getDt_txt()));
            fourthHour.setText(dateFormatter.getHour(weatherResult.getList().get(index + 3).getDt_txt()));
            fifthHour.setText(dateFormatter.getHour(weatherResult.getList().get(index + 4).getDt_txt()));
            sixthHour.setText(dateFormatter.getHour(weatherResult.getList().get(index + 5).getDt_txt()));
            seventhHour.setText(dateFormatter.getHour(weatherResult.getList().get(index + 6).getDt_txt()));

            setImage(firstImage, weatherResult.getList().get(index).getWeatherList().get(0).getIcon());
            setImage(secondImage, weatherResult.getList().get(index + 1).getWeatherList().get(0).getIcon());
            setImage(thirdImage, weatherResult.getList().get(index + 2).getWeatherList().get(0).getIcon());
            setImage(fourthImage, weatherResult.getList().get(index + 3).getWeatherList().get(0).getIcon());
            setImage(fifthImage, weatherResult.getList().get(index + 4).getWeatherList().get(0).getIcon());
            setImage(sixthImage, weatherResult.getList().get(index + 5).getWeatherList().get(0).getIcon());
            setImage(seventhImage, weatherResult.getList().get(index + 6).getWeatherList().get(0).getIcon());


            firstHours.setText(dateFormatter.getHour(weatherResult.getList().get(index).getDt_txt()));
            secondHours.setText(dateFormatter.getHour(weatherResult.getList().get(index + 1).getDt_txt()));
            thirdHours.setText(dateFormatter.getHour(weatherResult.getList().get(index + 2).getDt_txt()));
            fourthHours.setText(dateFormatter.getHour(weatherResult.getList().get(index + 3).getDt_txt()));
            fifthHours.setText(dateFormatter.getHour(weatherResult.getList().get(index + 4).getDt_txt()));
            sixthHours.setText(dateFormatter.getHour(weatherResult.getList().get(index + 5).getDt_txt()));
            seventhHours.setText(dateFormatter.getHour(weatherResult.getList().get(index + 6).getDt_txt()));

            setImage(firstImages, weatherResult.getList().get(index).getWeatherList().get(0).getIcon());
            setImage(secondImages, weatherResult.getList().get(index + 1).getWeatherList().get(0).getIcon());
            setImage(thirdImages, weatherResult.getList().get(index + 2).getWeatherList().get(0).getIcon());
            setImage(fourthImages, weatherResult.getList().get(index + 3).getWeatherList().get(0).getIcon());
            setImage(fifthImages, weatherResult.getList().get(index + 4).getWeatherList().get(0).getIcon());
            setImage(sixthImages, weatherResult.getList().get(index + 5).getWeatherList().get(0).getIcon());
            setImage(seventhImages, weatherResult.getList().get(index + 6).getWeatherList().get(0).getIcon());


        }
    }


    private void setImage(final ImageView imageView, final String value) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                switch (value) {
                    case "01d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.clear_sky));
                        break;
                    case "01n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.clear_sky));
                        break;
                    case "02d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.few_clouds));
                        break;
                    case "02n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.few_clouds));
                        break;
                    case "03d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
                        break;
                    case "03n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
                        break;
                    case "04d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
                        break;
                    case "04n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
                        break;
                    case "09d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                        break;
                    case "09n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                        break;
                    case "10d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.heavy_rain));
                        break;
                    case "10n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.heavy_rain));
                        break;
                    case "11d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.storm));
                        break;
                    case "11n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.storm));
                        break;
                    case "13d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                        break;
                    case "13n":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                        break;
                    case "50d":
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.mist));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cloud));

                }
            }
        });

    }
}