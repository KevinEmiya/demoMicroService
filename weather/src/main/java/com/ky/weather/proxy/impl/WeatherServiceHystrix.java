package com.ky.weather.proxy.impl;

import com.ky.weather.model.CurrentWeatherInfo;
import com.ky.weather.proxy.WeatherService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WeatherServiceHystrix implements WeatherService {

    private static final Log logger = LogFactory.getLog(WeatherServiceHystrix.class);

    @Override
    public CurrentWeatherInfo getRealTimeWeather(String key, String location) {
        logger.error("Invoking weather api failed: key: " + key + ", location: " + location);
        return null;
    }
}
