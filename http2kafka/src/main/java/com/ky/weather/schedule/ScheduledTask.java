package com.ky.weather.schedule;

import com.ky.weather.proxy.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Autowired
    WeatherService weatherService;

    @Value("${weather-api.seniverse.api-key}")
    String apiKey;

    @Value("${weather-api.seniverse.location}")
    String location;

    //@Scheduled(fixedDelay = 60000)
    public void queryWeather()
    {
        try {
            System.out.println(weatherService.getRealTimeWeather(apiKey, location));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
