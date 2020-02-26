package com.bhu.weatherforecast.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bhu.weatherforecast.models.Weathercurrent;
import com.bhu.weatherforecast.repositories.WeatherRepo;

public class Weatherviewmodel extends ViewModel {

    public WeatherRepo weatherRepo;
    private LiveData<Weathercurrent> weatherLiveData;
    private LiveData<Boolean> isdataerror;

   public void initiliaze(){
       weatherRepo = WeatherRepo.getInstance();

       weatherLiveData = weatherRepo.getWeatherdata();
       isdataerror = weatherRepo.getIsdataError();
   }

    public void refreshdata(){
        weatherRepo = new WeatherRepo();
        weatherLiveData = weatherRepo.getWeatherdata();
        isdataerror = weatherRepo.getIsdataError();
    }

    public LiveData<Weathercurrent> getWeatherLiveData() {
        return weatherLiveData;
    }

    public LiveData<Boolean> getIsdataerror() {
        return isdataerror;
    }


}
