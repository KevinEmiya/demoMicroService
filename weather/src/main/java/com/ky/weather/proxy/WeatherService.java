package com.ky.weather.proxy;

import com.ky.weather.model.CurrentWeatherInfo;
import com.ky.weather.proxy.impl.WeatherServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value="weather-api", path = "/v3", fallback = WeatherServiceHystrix.class)
public interface WeatherService {

    @GetMapping("/weather/now.json")
    CurrentWeatherInfo getRealTimeWeather(@RequestParam("key") String key, @RequestParam("location") String location);
}
