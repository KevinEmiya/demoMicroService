package com.ky.demo.scdf.processor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("poller")
public class HttpClientCronProperties {

    private String cron = "";

    private String fixedDelay = "";

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getFixedDelay() {
        return fixedDelay;
    }

    public void setFixedDelay(String fixedDelay) {
        this.fixedDelay = fixedDelay;
    }

    @Override
    public String toString() {
        return "HttpClientCronProperties{" +
                "cron='" + cron + '\'' +
                ", fixedDelay='" + fixedDelay + '\'' +
                '}';
    }
}
