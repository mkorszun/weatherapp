package com.weatherapp.weather.history;

import com.weatherapp.weather.domain.Weather;
import java.util.Collection;

public interface HistoryService {
    Collection<Weather> list(String location);

    void add(String location, Weather weather);
}
