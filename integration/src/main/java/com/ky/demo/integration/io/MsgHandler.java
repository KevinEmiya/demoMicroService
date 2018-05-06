package com.ky.demo.integration.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ky.demo.integration.io.interceptor.RequestLogInterceptor;
import com.ky.demo.integration.io.interceptor.ResponseErrorInterceptor;
import com.ky.demo.integration.model.Adder;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.messaging.PollableChannel;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                .handle(Kafka.outboundGateway(kafkaTemplate()))
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

    @Value("${kafka.broker.address}")
    private String brokerAddress;

    @Bean
    public ReplyingKafkaTemplate<String, String, String> kafkaTemplate() {
        return new ReplyingKafkaTemplate<>(pf(), replyContainer(cf()));
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> replyContainer(
            ConsumerFactory<String, String> cf) {
        ContainerProperties containerProperties = new ContainerProperties("kReplies");
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }

    @Bean
    public ProducerFactory<String, String> pf() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, String> cf() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "testGroup");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public NewTopic kRequests() {
        return new NewTopic("kRequests", 10, (short) 2);
    }

    @Bean
    public NewTopic kReplies() {
        return new NewTopic("kReplies", 10, (short) 2);
    }
}
