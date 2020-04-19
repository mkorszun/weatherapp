package com.weatherapp.weather;

public class WeatherNotFoundException extends WeatherException {
    public WeatherNotFoundException(Exception e) {
        super(e);
    }
}
