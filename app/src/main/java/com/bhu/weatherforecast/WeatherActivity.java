package com.bhu.weatherforecast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bhu.weatherforecast.models.Weathercurrent;
import com.bhu.weatherforecast.viewmodels.Weatherviewmodel;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    private LinearLayout dialog;
    private TextView address, time, status, temp, temp_min, temp_max, windspeed, winddir, cloudcover, pressure, humidity, about;
    private ImageView weathericon;
    private Weatherviewmodel weatherviewmodel;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private SwipeRefreshLayout swipe;
    public static Context mContext;
    private Double temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mContext = this;
        relativeLayout = findViewById(R.id.mainContainer);
        dialog = (LinearLayout) findViewById(R.id.mainprogress);
        dialog.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
        initviews();
        weatherviewmodel.initiliaze();
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshcurrentdata();
            }
        });
        getweatherLivedata();
        linearLayout = findViewById(R.id.forecast);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeatherActivity.this,Forecastactivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });
    }

    private void refreshcurrentdata(){
        dialog.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
        weatherviewmodel.refreshdata();
        getweatherLivedata();
    }

    private void initviews() {
        weathericon = findViewById(R.id.weathericon);
        address = findViewById(R.id.address);
        time = findViewById(R.id.time);
        status = findViewById(R.id.status);
        temp = findViewById(R.id.temp);
        windspeed = findViewById(R.id.windspeed);
        cloudcover = findViewById(R.id.cloudcover);
        winddir = findViewById(R.id.winddir);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        about = findViewById(R.id.about);
        swipe = findViewById(R.id.swipe);
        weatherviewmodel = ViewModelProviders.of(this).get(Weatherviewmodel.class);
    }
    private void getweatherLivedata() {
        weatherviewmodel.getIsdataerror().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    dialog.setVisibility(View.GONE);
                    AlertDialog alertDialog = new AlertDialog.Builder(WeatherActivity.this).create();
                    alertDialog.setTitle("Network Error");
                    alertDialog.setMessage("Please Make Sure Connect to Internet");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Tap To TryAgain",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    refreshcurrentdata();
                                }
                            });
                    alertDialog.show();
                }else {
                    dialog.setVisibility(View.GONE);
                }
            }
        });
        weatherviewmodel.getWeatherLiveData().observe(this, new Observer<Weathercurrent>() {
            @Override
            public void onChanged(Weathercurrent weather) {
                dialog.setVisibility(View.GONE);
                swipe.setRefreshing(false);
                relativeLayout.setVisibility(View.VISIBLE);
                String timec = getDate(weather.getDt());
                String sunrise = getDate(weather.getSys().getSunrise());
                String sunset = getDate(weather.getSys().getSunset());
                time.setText(timec);
                address.setText(weather.getName());
                status.setText(weather.getWeather().get(0).getDescription());
                String url = "http://openweathermap.org/img/w/" + String.valueOf(weather.getWeather().get(0).getIcon()) + ".png";
                Glide.with(WeatherActivity.this).load(url).into(weathericon);
                temp.setText(String.valueOf(weather.getMain().getTemp()) + "°C");
                windspeed.setText(String.valueOf(sunrise));
                cloudcover.setText(String.valueOf(weather.getClouds().getAll()) + "%");
                winddir.setText(sunset);
                pressure.setText(String.valueOf(weather.getMain().getPressure()));
                humidity.setText(String.valueOf(weather.getMain().getHumidity()));
//                if (weather.getCurrent().getIsDay().equals("yes")) {
//                    about.setText("Day");
//                } else {
//                    about.setText("Night");
//                }
            }
        });
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy hh:mm a", cal).toString();
        return date;
    }
}
