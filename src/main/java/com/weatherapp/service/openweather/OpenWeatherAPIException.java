package com.weatherapp.service.openweather;

public class OpenWeatherAPIException extends Exception {
    public OpenWeatherAPIException(String msg) {
        super(msg);
    }
}
