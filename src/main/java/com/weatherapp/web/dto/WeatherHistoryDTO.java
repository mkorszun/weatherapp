package com.weatherapp.web.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherHistoryDTO {
    @SerializedName("avg_temp") private int avgTemp;
    @SerializedName("avg_pressure") private int avgPressure;
    @SerializedName("history") private List<WeatherDTO> history;

    public WeatherHistoryDTO(int avgTemp, int avgPressure, List<WeatherDTO> history) {
        this.avgTemp = avgTemp;
        this.avgPressure = avgPressure;
        this.history = history;
    }
}
