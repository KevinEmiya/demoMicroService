package com.ky.weather.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CurrentWeatherInfo implements Serializable {

    @Data
    public static class WeatherLocation
    {
        String id;
        String name;
        String country;
        String timezone;
        String timezone_offset;
    }

    @Data
    public static class WeatherDetail
    {
        String text;
        String code;
        String temperature;
        String feels_like;
        String pressure;
        String humidity;
        String visibility;
        String wind_direction;
        String wind_direction_degree;
        String wind_speed;
        String wind_scale;
        String clouds;
        String dew_point;
    }

    @Data
    public static class WeatherResultItem
    {
        WeatherLocation location;
        Date last_update;
        WeatherDetail now;
    }

    List<WeatherResultItem> results;
}
