package com.bhu.weatherforecast.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bhu.weatherforecast.api.RetrofitClient;
import com.bhu.weatherforecast.api.Weatherapi;
import com.bhu.weatherforecast.models.Weatherforecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastRepo {

    private static ForecastRepo instance;
    private MutableLiveData<Weatherforecast> data = new MutableLiveData<>();
    //for failure case checkin
    private MutableLiveData<Boolean> forecastdataerror = new MutableLiveData<Boolean>();

    public static ForecastRepo getInstance(){
        if(instance == null){
            instance = new ForecastRepo();
        }
        return instance;
    }


    public LiveData<Weatherforecast> getForecastdata() {
        Weatherapi weatherapi = RetrofitClient.getInstance().getWeatherApi();

        Call<Weatherforecast> call = weatherapi.getForecast("Bangalore, India", "4c078fa6cd8a44de93b2bbfeaa7df6f6","metric","7");

        call.enqueue(new Callback<Weatherforecast>() {
            @Override
            public void onResponse(Call<Weatherforecast> call, Response<Weatherforecast> response) {
                if (response.isSuccessful()) {
                    final Weatherforecast messages = response.body();
                    data.setValue(messages);
                } else {
                    forecastdataerror.setValue(true);
                }

            }

            @Override
            public void onFailure(Call<Weatherforecast> call, Throwable t) {
                forecastdataerror.setValue(true);
            }
        });
        return data;
    }

    public LiveData<Boolean> getforecasterror(){
        return forecastdataerror;
    }

}
