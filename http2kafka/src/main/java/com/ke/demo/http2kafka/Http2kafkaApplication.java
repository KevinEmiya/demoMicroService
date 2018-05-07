package com.ke.demo.http2kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.ke.demo.http2kafka.proxy"})
@EnableScheduling
public class Http2kafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Http2kafkaApplication.class, args);
	}
}
