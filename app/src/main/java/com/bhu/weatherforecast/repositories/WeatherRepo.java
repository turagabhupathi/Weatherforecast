package com.bhu.weatherforecast.repositories;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bhu.weatherforecast.api.RetrofitClient;
import com.bhu.weatherforecast.api.Weatherapi;
import com.bhu.weatherforecast.models.Weathercurrent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepo {

    private static WeatherRepo instance;
    private MutableLiveData<Weathercurrent> data = new MutableLiveData<>();
    //for failure case checkin
    private MutableLiveData<Boolean> isdataerror = new MutableLiveData<Boolean>();

    public static WeatherRepo getInstance(){
        if(instance == null){
            instance = new WeatherRepo();
        }
        return instance;
    }


    public LiveData<Weathercurrent> getWeatherdata() {
        Weatherapi weatherapi = RetrofitClient.getInstance().getWeatherApi();

        Call<Weathercurrent> call = weatherapi.getCurrentWeather("Bangalore, India", "4c078fa6cd8a44de93b2bbfeaa7df6f6", "metric");

        call.enqueue(new Callback<Weathercurrent>() {
            @Override
            public void onResponse(Call<Weathercurrent> call, Response<Weathercurrent> response) {
                if (response.isSuccessful()) {
                    final Weathercurrent messages = response.body();
                    data.setValue(messages);
                } else {
                    isdataerror.setValue(true);
                }

            }

            @Override
            public void onFailure(Call<Weathercurrent> call, Throwable t) {
                Log.d("network", t.getMessage());
                isdataerror.setValue(true);
            }
        });
        return data;
    }

    public LiveData<Boolean> getIsdataError(){
        return isdataerror;
    }

}
