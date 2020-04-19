package com.weatherapp.weather.helper;

public class Utils {
    private static final double KELVIN_CELSIUS_SHIFT = 273.15d;

    public static double fromKelvinToCelsius(double kelvinTemp) {
        return kelvinTemp - KELVIN_CELSIUS_SHIFT;
    }
}
