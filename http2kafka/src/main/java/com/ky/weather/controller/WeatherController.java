package com.ky.weather.controller;

import com.ky.weather.io.CurrentWeatherEvent;
import com.ky.weather.model.CurrentWeatherInfo;
import com.ky.weather.proxy.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@EnableBinding({CurrentWeatherEvent.class})
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    CurrentWeatherEvent weatherEvent;

    @Value("${weather-api.seniverse.api-key}")
    String apiKey;

    @GetMapping("/{location}")
    @ResponseBody
    CurrentWeatherInfo getCityWeather(@PathVariable String location){
        CurrentWeatherInfo ret = weatherService.getRealTimeWeather(apiKey, location);
        String text = ret.getResults().get(0).getNow().getText();
        System.out.println("message to send: " + text);
        weatherEvent.output().send(MessageBuilder.withPayload("message: " + text).build());
        return ret;
    }
}
