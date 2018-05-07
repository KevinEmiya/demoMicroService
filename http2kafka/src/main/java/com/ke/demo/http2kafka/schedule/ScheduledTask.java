package com.ke.demo.http2kafka.schedule;

import com.ke.demo.http2kafka.proxy.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
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
