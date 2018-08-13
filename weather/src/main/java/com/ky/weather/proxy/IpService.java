package com.ky.weather.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value="ip-api", path = "/service")
public interface IpService {

    @GetMapping("/getIpInfo.php")
    String getIpInfo(@RequestParam("ip") String ip);
}
