package com.ke.demo.http2kafka.io;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface RabbitIO {

    String EVENT_RABBIT_IN = "event_rabbit_in";

    @Input(RabbitIO.EVENT_RABBIT_IN)
    SubscribableChannel input();
}
