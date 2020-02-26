package com.bhu.weatherforecast.adapters;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.bhu.weatherforecast.R;
import com.bhu.weatherforecast.WeatherActivity;
import com.bhu.weatherforecast.models.Listf;
import com.bhu.weatherforecast.models.Weatherforecast;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Forecastadapter extends RecyclerView.Adapter<Forecastadapter.ViewHolder> {
    private Weatherforecast weatheradapters;
    private Context mCtx;


    public Forecastadapter(Weatherforecast weatheradapters, Context mCtx) {
        this.weatheradapters = weatheradapters;
        this.mCtx = mCtx;
    }

    @Override
    public Forecastadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_weather, parent, false);
        return new Forecastadapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final Forecastadapter.ViewHolder holder, int position) {
        final Listf weatherforecst = weatheradapters.getListf().get(position);
        String date = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(weatherforecst.getDt() * 1000));
        holder.time.setText(date);
        holder.status.setText(weatherforecst.getWeather().get(0).getDescription());
        String url = "http://openweathermap.org/img/w/" + String.valueOf(weatherforecst.getWeather().get(0).getIcon()) + ".png";
        Glide.with(mCtx).load(url).into(holder.weathericon);
        holder.temp.setText(String.valueOf(weatherforecst.getMain().getTemp()) + "°C");
        holder.windspeed.setText(String.valueOf(weatherforecst.getMain().getPressure()));
        holder.cloudcover.setText(String.valueOf(weatherforecst.getMain().getHumidity()) + "%");
        holder.winddir.setText(String.valueOf(weatherforecst.getWeather().get(0).getId())+ "/hr"); // tempory data adjust
        holder.pressure.setText(String.valueOf(weatherforecst.getMain().getPressure()));
        holder.humidity.setText(String.valueOf(weatherforecst.getMain().getHumidity()));
    }

    @Override
    public int getItemCount() {
        return weatheradapters.getListf().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView address,time,status,temp,temp_min,temp_max,windspeed,winddir,cloudcover,pressure,humidity,about;
        private ImageView weathericon;

        public ViewHolder(View itemView) {
            super(itemView);

            weathericon = itemView.findViewById(R.id.weathericon);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
            temp = itemView.findViewById(R.id.temp);
            windspeed = itemView.findViewById(R.id.windspeed);
            cloudcover = itemView.findViewById(R.id.cloudcover);
            winddir = itemView.findViewById(R.id.winddir);
            pressure = itemView.findViewById(R.id.pressure);
            humidity = itemView.findViewById(R.id.humidity);
            about = itemView.findViewById(R.id.about);

        }
    }
}
