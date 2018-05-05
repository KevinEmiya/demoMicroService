package com.ky.demo.integration.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ky.demo.integration.io.interceptor.RequestLogInterceptor;
import com.ky.demo.integration.io.interceptor.ResponseErrorInterceptor;
import com.ky.demo.integration.model.Adder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.PollableChannel;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableIntegration
public class MsgHandler {

    @Autowired
    InputMsgService msgService;

    @Bean("requestChannel")
    public PollableChannel requestChannel() {
        return new QueueChannel();
    }

    @Bean("replyChannel")
    public PollableChannel replyChannel() {
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow httpGet() {

        RestTemplate template = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RequestLogInterceptor());
        template.setInterceptors(interceptors);
        template.setErrorHandler(new ResponseErrorInterceptor());

        return IntegrationFlows.from("requestChannel")
                .handle(Http.outboundGateway("http://localhost:2333/course/add", template)
                                .httpMethod(HttpMethod.POST)
                                .expectedResponseType(String.class)
                        , e -> e.poller(Pollers.fixedRate(5, TimeUnit.SECONDS)))
                .channel(replyChannel())
                .handle(msgService, "checkAndHandleMessage"
                        , e -> e.poller(Pollers.fixedRate(5, TimeUnit.SECONDS)))
                .get();
    }

    private static final Gson GSON = new GsonBuilder().create();

    @InboundChannelAdapter(channel = "requestChannel", poller = @Poller(fixedRate = "5000"))
    public HttpEntity<String> requestBody() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(GSON.toJson(new Adder(1, 2)), headers);
        return entity;
    }
}
