package com.weatherapp.service;

public class WeatherCurrent {
    private double temp;
    private double pressure;
    private boolean umbrella;

    public WeatherCurrent(double temp, double pressure, boolean umbrella) {
        this.temp = temp;
        this.pressure = pressure;
        this.umbrella = umbrella;
    }
}
