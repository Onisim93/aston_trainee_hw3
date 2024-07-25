package org.example.aston_trainee_hw3.weather_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
public class WeatherData {

    private Location location;
    private Current current;
    private Error error;

    @Getter
    @Setter
    public static class Error {
        private String code;
        private String message;
    }

    @Getter
    @Setter
    public static class Location {
        private String name;
        private String region;
        private String country;
        private Double lat;
        private Double lon;
        private String localtime;
    }

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("last_updated_epoch")
        private Integer lastUpdatedEpoch;
        @JsonProperty("last_updated")
        private String lastUpdated;
        @JsonProperty("temp_c")
        private Double tempC;
        private Double tempF;
        private Integer isDay;
        private Condition condition;
        private Double windMph;
        private Double windKph;
        private Integer windDegree;
        private String windDir;
        private Double pressureMb;
        private Double pressureIn;
        private Double precipMm;
        private Double precipIn;
        private Integer humidity;
        private Integer cloud;
        private Double feelslikeC;
        private Double feelslikeF;
        private Double visKm;
        private Double visMiles;
        private Double uv;
        private Double gustMph;
        private Double gustKph;


        @Getter
        @Setter
        public static class Condition {
            private String text;
            private String icon;
            private Integer code;
        }
    }
}
