package com.weatherapp.weather.domain;

import com.google.gson.annotations.SerializedName;
import java.util.Collection;

public class WeatherHistory {
    @SerializedName("avg_temp") private double avgTemp;
    @SerializedName("avg_pressure") private double avgPressure;
    @SerializedName("list") private Collection<Weather> history;

    public WeatherHistory(double avgTemp, double avgPressure, Collection<Weather> history) {
        this.avgTemp = avgTemp;
        this.avgPressure = avgPressure;
        this.history = history;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getAvgPressure() {
        return avgPressure;
    }

    public Collection<Weather> getHistory() {
        return history;
    }
}
