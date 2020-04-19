package com.weatherapp.weather.openweather;

import java.util.List;

public class OpenWeatherAPIResponse {

    private Main main;
    private List<Weather> weather;

    OpenWeatherAPIResponse(Main main, List<Weather> weather) {
        this.main = main;
        this.weather = weather;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    Main getMain() {
        return main;
    }

    static class Main {
        private double temp;
        private double pressure;

        Main(double temp, double pressure) {
            this.temp = temp;
            this.pressure = pressure;
        }

        double getPressure() {
            return pressure;
        }

        double getTemp() {
            return temp;
        }
    }

    public static class Weather {
        private String main;

        public Weather(String main) {
            this.main = main;
        }

        String getMain() {
            return main;
        }
    }
}
