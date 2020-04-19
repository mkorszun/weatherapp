package com.weatherapp.service.openweather;

import java.util.List;

public class OpenWeatherAPIResponse {

    private Main main;
    private List<Weather> weather;

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public static class Main {
        private double temp;
        private int pressure;

        public int getPressure() {
            return pressure;
        }

        public double getTemp() {
            return temp;
        }
    }

    public static class Weather {
        private String main;

        public String getMain() {
            return main;
        }
    }
}
