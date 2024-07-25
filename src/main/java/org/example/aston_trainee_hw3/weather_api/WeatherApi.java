package org.example.aston_trainee_hw3.weather_api;

import org.example.aston_trainee_hw3.weather_api.exception.WeatherApiException;
import org.example.aston_trainee_hw3.weather_api.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class provides methods to interact with an external weather API to retrieve current weather conditions for a specified city.
 */
@Component
public class WeatherApi {

    private final String URL = "http://api.weatherapi.com/v1/current.json";
    private final String KEY = "c8d70ac73e2f4639b04132653231203";
    private final String AQI = "no";

    private final Map<String, WeatherData> weatherData;

    private final String MESSAGE_GOOD_WEATHER_CONDITION = "Хорошая погода для посещения открытых парков и заповедников.";
    private final String MESSAGE_BAD_WEATHER_CONDITION = "В связи с погодными условиями, рекоммендуем к посещению закрытые заведения, такие как музеи и театры.";

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.weatherData = new ConcurrentHashMap<>();
    }


    private String createRequestUrlByCityName(String cityName) {
        return URL + "?key=" + KEY +
                "&aqi=" + AQI +
                "&q=" + cityName;
    }

    public String getWeatherConditionByLocalityName(String localityName) throws RestClientException {
        WeatherData wd;

        if (weatherData.containsKey(localityName.toLowerCase())) {
            wd = weatherData.get(localityName.toLowerCase());

            String lastUpdatedWeatherData = wd.getCurrent().getLastUpdated();
            LocalDateTime lastUpdateDateTime = LocalDateTime.parse(lastUpdatedWeatherData, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            long minutesBetweenLastUpdateAndCurrentDates = ChronoUnit.MINUTES.between(lastUpdateDateTime, LocalDateTime.now());

            if (minutesBetweenLastUpdateAndCurrentDates > 30) {
                wd = requestWeatherData(localityName).getBody();
                weatherData.put(localityName.toLowerCase(), wd);
            }
        }
        else {
            wd = requestWeatherData(localityName).getBody();
            weatherData.put(localityName.toLowerCase(), wd);
        }

        return getRecommendationsFotVisitingAttractionsByWeatherCondition(Objects.requireNonNull(wd).getCurrent().getCondition().getCode());
    }

    private ResponseEntity<WeatherData> requestWeatherData(String locality) throws RestClientException{
        String urlWithLocality = createRequestUrlByCityName(locality);
        ResponseEntity<WeatherData> response = restTemplate.getForEntity(urlWithLocality, WeatherData.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response;
        }
        throw new WeatherApiException(Objects.requireNonNull(response.getBody()).getError().getMessage());
    }


    private String getRecommendationsFotVisitingAttractionsByWeatherCondition(Integer conditionCode) {
        return conditionCode >= 1000 && conditionCode <= 1009 ? MESSAGE_GOOD_WEATHER_CONDITION : MESSAGE_BAD_WEATHER_CONDITION;
    }



}
