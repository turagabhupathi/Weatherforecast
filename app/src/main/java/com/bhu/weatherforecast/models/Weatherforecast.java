package com.bhu.weatherforecast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weatherforecast {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("list")
    @Expose
    private java.util.List<com.bhu.weatherforecast.models.Listf> list = null;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public java.util.List<com.bhu.weatherforecast.models.Listf> getListf() {
        return list;
    }

    public void setListf(java.util.List<com.bhu.weatherforecast.models.Listf> list) {
        this.list = list;
    }


}