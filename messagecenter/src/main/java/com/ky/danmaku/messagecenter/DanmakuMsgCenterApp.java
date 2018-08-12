package com.ky.danmaku.messagecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class DanmakuMsgCenterApp {

	public static void main(String[] args) {
		SpringApplication.run(DanmakuMsgCenterApp.class, args);
	}
}
