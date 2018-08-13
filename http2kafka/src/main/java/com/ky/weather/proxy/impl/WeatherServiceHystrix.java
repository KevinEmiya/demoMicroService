package com.ky.weather.proxy.impl;

import com.ky.weather.model.CurrentWeatherInfo;
import com.ky.weather.proxy.WeatherService;

public class WeatherServiceHystrix implements WeatherService {

    @Override
    public CurrentWeatherInfo getRealTimeWeather(String key, String location) {
        System.out.println("Invocaton FAILED!!!!!!!");
        return null;
    }
}
