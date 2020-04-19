package com.weatherapp.weather.openweather;

import com.weatherapp.weather.WeatherException;
import com.weatherapp.weather.domain.Weather;
import com.weatherapp.weather.helper.Utils;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

import static java.util.Arrays.asList;

public class OpenWeatherServiceTest {

    private static OpenWeatherAPIClient mockOpenWeatherClient(double temp, double pressure, List<String> conditions) {
        OpenWeatherAPIResponse.Main main = new OpenWeatherAPIResponse.Main(temp, pressure);

        List<OpenWeatherAPIResponse.Weather> weather = conditions.stream()
            .map(OpenWeatherAPIResponse.Weather::new)
            .collect(Collectors.toList());

        return new OpenWeatherAPIClient() {
            @Override public OpenWeatherAPIResponse weather(String location) {
                return new OpenWeatherAPIResponse(main, weather);
            }
        };
    }

    @Test
    public void currentWeatherRequiresUmbrella() throws WeatherException {
        int temp = 300;
        int pressure = 1025;
        List<String> conditions = asList("Thunderstorm");

        OpenWeatherService service = new OpenWeatherService(mockOpenWeatherClient(temp, pressure, conditions));
        Weather currentWeather = service.current("Berlin");

        Assert.assertEquals(currentWeather.getTemp(), Utils.fromKelvinToCelsius(temp), 0);
        Assert.assertEquals(currentWeather.getPressure(), pressure, 0);
        Assert.assertTrue(currentWeather.isUmbrella());
    }

    @Test
    public void currentWeatherRequiresNoUmbrella() throws WeatherException {
        int temp = 300;
        int pressure = 1025;
        List<String> conditions = asList("Clear");

        OpenWeatherService service = new OpenWeatherService(mockOpenWeatherClient(temp, pressure, conditions));
        Weather currentWeather = service.current("Berlin");

        Assert.assertEquals(currentWeather.getTemp(), Utils.fromKelvinToCelsius(temp), 0);
        Assert.assertEquals(currentWeather.getPressure(), pressure, 0);
        Assert.assertFalse(currentWeather.isUmbrella());
    }
}
