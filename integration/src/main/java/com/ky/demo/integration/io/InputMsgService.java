package com.ky.demo.integration.io;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InputMsgService {

    public void checkAndHandleMessage(@Payload String payload, @Headers Map<String, Object> headers) {
        System.out.println(headers);
        System.out.println(payload);
    }
}
