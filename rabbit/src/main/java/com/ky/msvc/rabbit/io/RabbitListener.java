package com.ky.msvc.rabbit.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(RabbitIO.class)
public class RabbitListener {

    @Autowired
    RabbitIO rabbitIO;

    @StreamListener(RabbitIO.EVENT_RABBIT_IN)
    public void onMsg(String msg) {
        System.out.println("recvd: " + msg);
    }

    public void send(String msg){
        rabbitIO.output().send(MessageBuilder.withPayload(msg).build());
    }
}
