package com.ky.danmaku.messagecenter.service;

import com.ky.danmaku.messagecenter.common.ResultBean;

public interface TestService {
    ResultBean getTestMsg();

    ResultBean setTestMsg(String msg);
}
