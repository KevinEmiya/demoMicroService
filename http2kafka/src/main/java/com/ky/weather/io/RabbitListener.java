package com.ky.weather.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(RabbitIO.class)
public class RabbitListener {

    @Autowired
    RabbitIO rabbitIO;

    @StreamListener(RabbitIO.EVENT_RABBIT_IN)
    public void onMsg(String msg) {
        System.out.println(msg);
    }
}
