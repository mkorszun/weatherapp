package com.weatherapp.service.openweather;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class OpenWeatherAPIClient {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "011147728b81cc804850a1cfbc4cc927";

    private static final String CITY_QUERY_PARAM = "q";
    private static final String API_KEY_QUERY_PARAM = "APPID";

    private String url;
    private String apiKey;

    public OpenWeatherAPIClient() {
        this(BASE_URL, API_KEY);
    }

    public OpenWeatherAPIClient(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }

    public OpenWeatherAPIResponse weather(String city) throws OpenWeatherAPIException {
        HttpResponse<OpenWeatherAPIResponse> response = Unirest.get(url)
            .queryString(CITY_QUERY_PARAM, city)
            .queryString(API_KEY_QUERY_PARAM, apiKey)
            .asObject(OpenWeatherAPIResponse.class);

        if (response.isSuccess()) {
            return response.getBody();
        } else {
            throw new OpenWeatherAPIException(response.getStatusText());
        }
    }
}
