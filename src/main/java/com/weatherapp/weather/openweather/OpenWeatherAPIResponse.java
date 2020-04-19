package com.weatherapp.weather.openweather;

import java.util.List;

public class OpenWeatherAPIResponse {

    private Main main;
    private List<Weather> weather;

    public OpenWeatherAPIResponse(Main main, List<Weather> weather) {
        this.main = main;
        this.weather = weather;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public static class Main {
        private double temp;
        private double pressure;

        public Main(double temp, double pressure) {
            this.temp = temp;
            this.pressure = pressure;
        }

        public double getPressure() {
            return pressure;
        }

        public double getTemp() {
            return temp;
        }
    }

    public static class Weather {
        private String main;

        public Weather(String main) {
            this.main = main;
        }

        public String getMain() {
            return main;
        }
    }
}
