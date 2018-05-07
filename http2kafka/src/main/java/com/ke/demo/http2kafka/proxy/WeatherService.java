package com.ke.demo.http2kafka.proxy;

import com.ke.demo.http2kafka.model.CurrentWeatherInfo;
import com.ke.demo.http2kafka.proxy.impl.WeatherServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="weather-api", path = "/v3", fallback = WeatherServiceHystrix.class)
public interface WeatherService {

    @GetMapping("/weather/now.json")
    CurrentWeatherInfo getRealTimeWeather(@RequestParam("key") String key, @RequestParam("location") String location);
}
