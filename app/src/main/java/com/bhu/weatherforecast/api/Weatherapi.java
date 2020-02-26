package com.bhu.weatherforecast.api;

import com.bhu.weatherforecast.models.Weathercurrent;
import com.bhu.weatherforecast.models.Weatherforecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Weatherapi {

    @GET("weather")
    Call<Weathercurrent> getCurrentWeather(@Query("q") String cityName, @Query("APPID") String apiKey,@Query("units") String units);

    @GET("forecast")
    Call<Weatherforecast> getForecast(@Query("q") String cityName, @Query("APPID") String apiKey, @Query("units") String units, @Query("cnt") String cnt);

}
