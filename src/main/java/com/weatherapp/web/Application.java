package com.weatherapp.web;

import com.google.gson.Gson;
import com.weatherapp.weather.WeatherNotFoundException;
import com.weatherapp.weather.WeatherServiceWithHistory;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;

public class Application {
    private static final Gson gson = new Gson();
    private static final WeatherServiceWithHistory weatherService = new WeatherServiceWithHistory();

    public static void main(String[] args) {

        before((req, res) -> {
            String location = req.queryParams("location");
            if (location == null || location.isEmpty()) {
                halt(422, "Location is missing");
            }
        });

        get("/current", (req, res) -> weatherService.current(req.queryParams("location")), gson::toJson);

        get("/history", (req, res) -> weatherService.history(req.queryParams("location")), gson::toJson);

        exception(WeatherNotFoundException.class, (exception, request, response) -> {
            response.status(404);
            response.body("No weather info for given location");
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body("Something went wrong");
        });

        after((req, res) -> res.type("application/json"));
    }
}
