package com.weatherapp.weather.openweather;

import com.weatherapp.weather.WeatherException;
import com.weatherapp.weather.WeatherNotFoundException;
import com.weatherapp.weather.WeatherService;
import com.weatherapp.weather.domain.Weather;
import com.weatherapp.weather.helper.Utils;
import java.util.List;

import static java.util.Arrays.asList;

public class OpenWeatherService implements WeatherService {
    private static List<String> UMBRELLA_CONDITIONS = asList("Thunderstorm", "Drizzle", "Rain");

    private OpenWeatherAPIClient client;

    public OpenWeatherService() {
        this(new OpenWeatherAPIClient());
    }

    public OpenWeatherService(OpenWeatherAPIClient client) {
        this.client = client;
    }

    @Override public Weather current(String location) throws WeatherException {
        try {
            OpenWeatherAPIResponse response = client.weather(location);

            double temp = temperatureInCelsius(response);
            double pressure = pressure(response);
            boolean umbrellaRequired = isUmbrellaRequired(response);

            return new Weather(temp, pressure, umbrellaRequired);
        } catch (OpenWeatherNotFoundException e) {
            throw new WeatherNotFoundException(e);
        } catch (Exception e) {
            throw new WeatherException(e);
        }
    }

    private double temperatureInCelsius(OpenWeatherAPIResponse response) {
        return Utils.fromKelvinToCelsius(response.getMain().getTemp());
    }

    private double pressure(OpenWeatherAPIResponse response) {
        return response.getMain().getPressure();
    }

    private boolean isUmbrellaRequired(OpenWeatherAPIResponse response) {
        return response.getWeather()
            .stream()
            .anyMatch((weather) -> UMBRELLA_CONDITIONS.contains(weather.getMain()));
    }
}
