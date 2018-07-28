package com.ky.msvc.rabbit.io;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface RabbitIO {

    String EVENT_RABBIT_IN = "event_rabbit_in";
    String EVENT_RABBIT_OUT = "event_rabbit_out";

    @Input(RabbitIO.EVENT_RABBIT_IN)
    SubscribableChannel input();

    @Output(RabbitIO.EVENT_RABBIT_OUT)
    SubscribableChannel output();
}
