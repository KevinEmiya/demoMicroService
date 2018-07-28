package com.ky.demo.scdf.processor.component;

import com.ky.demo.scdf.processor.config.HttpClientCronProperties;
import com.ky.demo.scdf.processor.io.HttpClientSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

@EnableBinding(HttpClientSource.class)
@EnableConfigurationProperties(HttpClientCronProperties.class)
public class HttpClientProcessor {

    private static final Log logger = LogFactory.getLog(HttpClientProcessor.class);

    @Autowired
    HttpClientCronProperties properties;

    @InboundChannelAdapter(value = HttpClientSource.SOURCE_EVENT,
            poller = @Poller(fixedDelay = "${poller.fixed-delay}"))
    public String httpPoll(){
        String retVal = "hello, my props are: " + properties;
        logger.info("Received Http Response: " + retVal);
        return retVal;
    }
}
