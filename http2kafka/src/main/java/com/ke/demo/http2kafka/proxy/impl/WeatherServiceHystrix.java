package com.ke.demo.http2kafka.proxy.impl;

import com.ke.demo.http2kafka.model.CurrentWeatherInfo;
import com.ke.demo.http2kafka.proxy.WeatherService;

public class WeatherServiceHystrix implements WeatherService {

    @Override
    public CurrentWeatherInfo getRealTimeWeather(String key, String location) {
        System.out.println("Invocaton FAILED!!!!!!!");
        return null;
    }
}
