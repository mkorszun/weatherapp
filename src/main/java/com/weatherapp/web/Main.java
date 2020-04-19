package com.weatherapp.web;

import com.google.gson.Gson;
import com.weatherapp.service.WeatherService;
import com.weatherapp.service.openweather.OpenWeatherService;
import com.weatherapp.web.dto.WeatherHistoryDTO;
import java.util.Collections;

import static spark.Spark.after;
import static spark.Spark.get;

public class Main {
    private static final Gson gson = new Gson();
    private static final WeatherService weatherService = new OpenWeatherService();

    public static void main(String[] args) {
        get("/_health", (req, res) -> "OK");

        get("/current", (req, res) -> weatherService.current(req.queryParams("location")), gson::toJson);

        get("/history", (req, res) -> new WeatherHistoryDTO(1, 1, Collections.emptyList()), gson::toJson);

        after((req, res) -> res.type("application/json"));
    }
}
