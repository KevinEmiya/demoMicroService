package com.ky.danmaku.messagecenter.service.impl;

import com.ky.danmaku.messagecenter.common.ResultBean;
import com.ky.danmaku.messagecenter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {


    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public ResultBean getTestMsg() {

        try {
            String msg = redisTemplate.opsForValue().get("test");
            return new ResultBean<>(msg);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }

    @Override
    public ResultBean setTestMsg(String msg) {

        try {
            redisTemplate.opsForValue().set("test", msg);
            return new ResultBean<>("set test msg: " + msg);
        } catch (Exception e) {
            return new ResultBean<>(e);
        }
    }
}
