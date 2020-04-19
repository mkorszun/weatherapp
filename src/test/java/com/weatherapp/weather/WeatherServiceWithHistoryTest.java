package com.weatherapp.weather;

import com.weatherapp.weather.domain.Weather;
import com.weatherapp.weather.domain.WeatherHistory;
import com.weatherapp.weather.history.InMemoryHistoryService;
import org.junit.Assert;
import org.junit.Test;

public class WeatherServiceWithHistoryTest {

    private WeatherServiceWithHistory weatherServiceWithHistory;

    public static WeatherService mockWeatherService(double temp, double pressure, boolean isUmbrellaRequired) {
        return city -> new Weather(temp, pressure, isUmbrellaRequired);
    }

    @Test
    public void currentReturnsCurrentWeatherForLocation() throws WeatherException {
        double temp = 25, pressure = 1025;
        boolean umbrellaRequired = false;

        weatherServiceWithHistory = new WeatherServiceWithHistory(
            mockWeatherService(temp, pressure, umbrellaRequired),
            new InMemoryHistoryService()
        );

        Weather weather = weatherServiceWithHistory.current("some location");

        Assert.assertEquals(temp, weather.getTemp(), 0);
        Assert.assertEquals(pressure, weather.getPressure(), 0);
        Assert.assertEquals(umbrellaRequired, weather.isUmbrella());
    }

    @Test
    public void historyReturnsLastResultsForLocation() throws WeatherException {
        double temp = 25, pressure = 1025;
        boolean umbrellaRequired = false;

        weatherServiceWithHistory = new WeatherServiceWithHistory(
            mockWeatherService(temp, pressure, umbrellaRequired),
            new InMemoryHistoryService()
        );

        String location = "some location";
        weatherServiceWithHistory.current(location);
        weatherServiceWithHistory.current(location);
        weatherServiceWithHistory.current(location);
        weatherServiceWithHistory.current("another location");

        WeatherHistory history = weatherServiceWithHistory.history(location);

        Assert.assertEquals(3, history.getHistory().size());
        Assert.assertEquals(25.0, history.getAvgTemp(), 0);
        Assert.assertEquals(1025, history.getAvgPressure(), 0);
    }

    @Test
    public void historyReturnsNoResultsForNeverQueriedLocation() {
        double temp = 25, pressure = 1025;
        boolean umbrellaRequired = false;

        weatherServiceWithHistory = new WeatherServiceWithHistory(
            mockWeatherService(temp, pressure, umbrellaRequired),
            new InMemoryHistoryService()
        );

        WeatherHistory history = weatherServiceWithHistory.history("some location");

        Assert.assertEquals(0, history.getHistory().size());
        Assert.assertEquals(0.0, history.getAvgTemp(), 0);
        Assert.assertEquals(0.0, history.getAvgPressure(), 0);
    }
}
