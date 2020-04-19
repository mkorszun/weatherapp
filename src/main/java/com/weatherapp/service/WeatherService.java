package com.weatherapp.service;

public abstract class WeatherService {

    public abstract WeatherCurrent current(String city);

    public abstract WeatherHistory history(String city);
}
