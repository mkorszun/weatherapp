package com.weatherapp.service.openweather;

import com.weatherapp.service.WeatherCurrent;
import com.weatherapp.service.WeatherHistory;
import com.weatherapp.service.WeatherService;
import java.util.List;

import static java.util.Arrays.asList;

public class OpenWeatherService extends WeatherService {

    private static List<String> UMBRELLA_CONDITIONS = asList("Thunderstorm", "Drizzle", "Rain");

    private OpenWeatherAPIClient client = new OpenWeatherAPIClient();

    @Override public WeatherCurrent current(String city) {
        try {
            OpenWeatherAPIResponse response = client.weather(city);

            return new WeatherCurrent(
                kelvinToCelsius(response.getMain().getTemp()),
                response.getMain().getPressure(),
                isUmbrellaRequired(response.getWeather().get(0).getMain())
            );
        } catch (OpenWeatherAPIException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public WeatherHistory history(String city) {
        return null;
    }

    private boolean isUmbrellaRequired(String condition) {
        return UMBRELLA_CONDITIONS.contains(condition);
    }

    private double kelvinToCelsius(double kelvinTemp) {
        return kelvinTemp - 273.15d;
    }
}
