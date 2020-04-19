package com.weatherapp.weather;

public class WeatherException extends Exception {
    public WeatherException(Exception e) {
        super(e);
    }
}
