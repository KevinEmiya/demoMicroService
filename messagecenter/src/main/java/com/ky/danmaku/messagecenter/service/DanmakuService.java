package com.ky.danmaku.messagecenter.service;

import com.ky.danmaku.messagecenter.common.ResultBean;

public interface DanmakuService {

    ResultBean getLatestDanmaku(int count);

    ResultBean addDanmaku(String danmaku);

    ResultBean clearAll();
}
