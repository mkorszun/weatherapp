package com.weatherapp.weather;

import com.weatherapp.weather.domain.Weather;
import com.weatherapp.weather.domain.WeatherHistory;
import com.weatherapp.weather.history.HistoryService;
import com.weatherapp.weather.history.InMemoryHistoryService;
import com.weatherapp.weather.openweather.OpenWeatherService;
import java.util.Collection;

public class WeatherServiceWithHistory {
    private WeatherService weatherService;
    private HistoryService historyService;

    public WeatherServiceWithHistory() {
        this(new OpenWeatherService(), new InMemoryHistoryService());
    }

    public WeatherServiceWithHistory(WeatherService weatherService, HistoryService historyService) {
        this.weatherService = weatherService;
        this.historyService = historyService;
    }

    public Weather current(String location) throws WeatherException {
        Weather current = weatherService.current(location);
        historyService.add(location, current);
        return current;
    }

    public WeatherHistory history(String location) {
        Collection<Weather> lastResults = historyService.list(location);

        double avgTemp = lastResults.stream()
            .mapToDouble(Weather::getTemp)
            .average()
            .orElse(0);

        double avgPressure = lastResults.stream()
            .mapToDouble(Weather::getPressure)
            .average()
            .orElse(0);

        return new WeatherHistory(avgTemp, avgPressure, lastResults);
    }
}
