package com.weatherapp.weather.openweather;

import java.util.logging.Logger;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;

public class OpenWeatherAPIClient {
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "011147728b81cc804850a1cfbc4cc927";

    private static final String LOCATION_QUERY_PARAM = "q";
    private static final String API_KEY_QUERY_PARAM = "APPID";

    private String url;
    private String apiKey;

    public OpenWeatherAPIClient() {
        this(BASE_URL, API_KEY);
    }

    public OpenWeatherAPIClient(String apiKey) {
        this(BASE_URL, apiKey);
    }

    public OpenWeatherAPIClient(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }

    private static OpenWeatherAPIResponse handleFailure(String location, HttpResponse<OpenWeatherAPIResponse> response) throws OpenWeatherException {
        switch (response.getStatus()) {
            case SC_NOT_FOUND:
                LOGGER.warning(String.format("Location not found: %s", location));
                throw new OpenWeatherNotFoundException("Location not found");
            default:
                LOGGER.severe(String.format("Location lookup failure: %s, %s", location, response.getStatusText()));
                throw new OpenWeatherException("Location lookup failure");
        }
    }

    public OpenWeatherAPIResponse weather(String location) throws OpenWeatherException {
        return Unirest.get(url)
            .queryString(LOCATION_QUERY_PARAM, location)
            .queryString(API_KEY_QUERY_PARAM, apiKey)
            .asObject(OpenWeatherAPIResponse.class)
            .ifSuccess(HttpResponse::getBody)
            .ifFailure((error) -> handleFailure(location, error))
            .getBody();
    }
}
