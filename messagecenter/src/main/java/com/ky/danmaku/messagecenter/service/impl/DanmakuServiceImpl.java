package com.ky.danmaku.messagecenter.service.impl;

import com.ky.danmaku.messagecenter.common.ResultBean;
import com.ky.danmaku.messagecenter.service.DanmakuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanmakuServiceImpl implements DanmakuService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private static final Log logger = LogFactory.getLog(DanmakuService.class);

    private static final String DANMAKU_KEY = "dmk";

    private static final int MAX_DANMAKU_CNT = 100;

    @Override
    public ResultBean getLatestDanmaku(int count) {
        if(count <= 0) {
            ResultBean result = new ResultBean();
            result.setCode(ResultBean.FAIL);
            result.setMsg("Msg count should be a positive number!");
            return result;
        }

        try {
            List<String> msgs = redisTemplate.opsForList().range(DANMAKU_KEY,0, count - 1);
            return new ResultBean<>(msgs);
        }
        catch (Exception e)
        {
            return new ResultBean<>(e);
        }
    }

    @Override
    public ResultBean addDanmaku(String danmaku) {
        try {
            List<String> msgs = redisTemplate.opsForList().range(DANMAKU_KEY, 1, -1);
            if(msgs.size() == MAX_DANMAKU_CNT) {
                String expMsg = redisTemplate.opsForList().rightPop(DANMAKU_KEY);
                logger.info("Msg cnt exceeds max cnt, expiring oldest msg: " + expMsg);
            }
            redisTemplate.opsForList().leftPush(DANMAKU_KEY, danmaku);
            logger.info("Added danmaku: " + danmaku);
            return new ResultBean<>("added: " + danmaku);
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
    }

    @Override
    public ResultBean clearAll() {
        try {
            redisTemplate.opsForList().trim(DANMAKU_KEY, 0, 0);
            redisTemplate.opsForList().leftPop(DANMAKU_KEY);
            logger.info("All danmaku cleared!");
            return new ResultBean<>("all danmaku cleared!");
        }
        catch (Exception e){
            return new ResultBean<>(e);
        }
    }
}
