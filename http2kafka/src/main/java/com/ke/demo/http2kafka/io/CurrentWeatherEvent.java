package com.ke.demo.http2kafka.io;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface CurrentWeatherEvent {

    String OUT_NAME = "currentWeather";

    @Output(CurrentWeatherEvent.OUT_NAME)
    MessageChannel output();

}
