package com.ky.danmaku.messagecenter.controller;

import com.ky.danmaku.messagecenter.common.ResultBean;
import com.ky.danmaku.messagecenter.service.DanmakuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/danmaku")
@Api("弹幕接口")
public class DanmakuController {

    @Autowired
    DanmakuService danmakuService;

    @GetMapping("/latest/{count}")
    public ResultBean getLatest(@PathVariable Integer count){
        return danmakuService.getLatestDanmaku(count);
    }

    @PutMapping("")
    public ResultBean add(@RequestBody String danmaku) {
        return danmakuService.addDanmaku(danmaku);
    }

    @DeleteMapping("")
    public ResultBean delete() {
        return danmakuService.clearAll();
    }

}
