package com.bhu.weatherforecast;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bhu.weatherforecast.adapters.Forecastadapter;
import com.bhu.weatherforecast.models.Listf;
import com.bhu.weatherforecast.models.Weatherforecast;
import com.bhu.weatherforecast.viewmodels.Forecastviewmodel;
import com.bhu.weatherforecast.viewmodels.Weatherviewmodel;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Forecastactivity extends AppCompatActivity {
    private RecyclerView recyclerViewforecasts;
    private RecyclerView.Adapter adapter;
    private LinearLayout dialog;
    private SwipeRefreshLayout swipe;
    private TextView city;
    private Forecastviewmodel forecastviewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        dialog = (LinearLayout) findViewById(R.id.mainprogress);
        dialog.setVisibility(View.VISIBLE);
        recyclerViewforecasts = (RecyclerView) findViewById(R.id.recycleviewforecasts);
        recyclerViewforecasts.setHasFixedSize(true);
        recyclerViewforecasts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        forecastviewmodel = ViewModelProviders.of(this).get(Forecastviewmodel.class);
        forecastviewmodel.initiliaze();
        city = findViewById(R.id.address);
        getweatherForecastdata();
        swipe = findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshcurrentdata();
            }
        });
        findViewById(R.id.backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void refreshcurrentdata(){
        dialog.setVisibility(View.VISIBLE);
        city.setVisibility(View.GONE);
        forecastviewmodel.refreshdata();
        getweatherForecastdata();
    }

    private void getweatherForecastdata() {
        forecastviewmodel.getforecastdataerror().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    dialog.setVisibility(View.GONE);
                    AlertDialog alertDialog = new AlertDialog.Builder(Forecastactivity.this).create();
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

        forecastviewmodel.getWeatherLiveData().observe(this, new Observer<Weatherforecast>() {
            @Override
            public void onChanged(Weatherforecast weatherforecast) {
                dialog.setVisibility(View.GONE);
                city.setVisibility(View.VISIBLE);
                swipe.setRefreshing(false);
                city.setText("Bengaluru, India");
                adapter = new Forecastadapter(weatherforecast, Forecastactivity.this);
                recyclerViewforecasts.setAdapter(adapter);
                dialog.setVisibility(View.GONE);
            }
        });
    }
}
