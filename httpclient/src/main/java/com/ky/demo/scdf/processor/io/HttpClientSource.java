package com.ky.demo.scdf.processor.io;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface HttpClientSource {

    public static final String SOURCE_EVENT = "scdf_processor_httpclient";

    @Output(SOURCE_EVENT)
    SubscribableChannel output();
}
