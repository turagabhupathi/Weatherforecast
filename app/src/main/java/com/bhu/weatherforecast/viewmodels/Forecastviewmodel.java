package com.bhu.weatherforecast.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bhu.weatherforecast.models.Weatherforecast;
import com.bhu.weatherforecast.repositories.ForecastRepo;

public class Forecastviewmodel extends ViewModel {

    public ForecastRepo weatherRepo;
    private LiveData<Weatherforecast> Forecastdata;
    private LiveData<Boolean> forecastdataerror;

   public void initiliaze(){
       weatherRepo = ForecastRepo.getInstance();

       Forecastdata = weatherRepo.getForecastdata();
      forecastdataerror = weatherRepo.getforecasterror();
   }

    public void refreshdata(){
        weatherRepo = new ForecastRepo();
        Forecastdata = weatherRepo.getForecastdata();
        forecastdataerror = weatherRepo.getforecasterror();
    }

    public LiveData<Weatherforecast> getWeatherLiveData() {
        return Forecastdata;
    }

   public LiveData<Boolean> getforecastdataerror() {
      return forecastdataerror;
   }


}
