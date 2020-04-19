package com.weatherapp.web.dto;

import com.google.gson.annotations.SerializedName;

public class WeatherDTO {
    @SerializedName("temp") private int temp;
    @SerializedName("pressure") private int pressure;
    @SerializedName("umbrella") private boolean umbrella;

    public WeatherDTO(int temp, int pressure, boolean umbrella) {
        this.temp = temp;
        this.pressure = pressure;
        this.umbrella = umbrella;
    }
}
