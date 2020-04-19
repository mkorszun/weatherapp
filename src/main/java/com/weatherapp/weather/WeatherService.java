package com.weatherapp.weather;

import com.weatherapp.weather.domain.Weather;

public interface WeatherService {
    Weather current(String city) throws WeatherException;
}
