package com.weatherapp.weather.domain;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("temp") private double temp;
    @SerializedName("pressure") private double pressure;
    @SerializedName("umbrella") private boolean umbrella;

    public Weather(double temp, double pressure, boolean umbrella) {
        this.temp = temp;
        this.pressure = pressure;
        this.umbrella = umbrella;
    }

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public boolean isUmbrella() {
        return umbrella;
    }
}
