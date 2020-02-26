package com.bhu.weatherforecast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Listf {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("main")
    @Expose
    private Mainf main;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weatherf> weather = null;

    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Mainf getMain() {
        return main;
    }

    public void setMain(Mainf main) {
        this.main = main;
    }

    public java.util.List<Weatherf> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weatherf> weather) {
        this.weather = weather;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

}