package com.ky.weather.controller;

import com.ky.weather.model.CurrentWeatherInfo;
import com.ky.weather.proxy.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@Api("天气信息查询")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @Value("${weather-api.seniverse.api-key}")
    String apiKey;

    @GetMapping("/{location}")
    @ResponseBody
    @ApiOperation("查询指定地点的天气, 支持中文/拼音/城市编码")
    CurrentWeatherInfo getCityWeather(@PathVariable String location){
        return weatherService.getRealTimeWeather(apiKey, location);
    }
}
