package com.ky.demo.msvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Word {
    String word;

    @JsonFormat(timezone = "UTC+8:00", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    LocalDateTime updateTime;

    @Override
    public String toString() {
        return "{" +
                "word='" + word + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
