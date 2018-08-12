package com.ky.danmaku.messagecenter.controller;

import com.ky.danmaku.messagecenter.annotation.BeanResult;
import com.ky.danmaku.messagecenter.common.ResultBean;
import com.ky.danmaku.messagecenter.service.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Api("测试用接口")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public ResultBean<String> getTestMsg(){
        return testService.getTestMsg();
    }

    @PostMapping("/test")
    public ResultBean<String> setTestMsg(@RequestBody String msg) {
        return testService.setTestMsg(msg);
    }

    @GetMapping("/advice")
    @BeanResult
    public String testAdvice() {
        return "test";
    }
}
