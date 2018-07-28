package com.ky.msvc.rabbit.controller;

import com.ky.msvc.rabbit.io.RabbitIO;
import com.ky.msvc.rabbit.io.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgCtrl {

    @Autowired
    RabbitListener listener;

    @PostMapping("")
    public void sendMsg(@RequestBody String msg){
        listener.send(msg);
    }
}
