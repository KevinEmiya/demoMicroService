package com.ky.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ky.weather.model.IpInfoModel;
import com.ky.weather.proxy.IpService;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

@RestController
@RequestMapping("/ip")
@Api("本机ip信息查询")
public class IpController {

    @Autowired
    IpService ipService;

    @Autowired
    ObjectMapper objectMapper;

    private static final Log logger = LogFactory.getLog(IpController.class);

    @GetMapping("")
    public IpInfoModel queryLocalIpInfo(HttpServletRequest request) {
        String ip = getIpAddr(request);
        String infoRaw = ipService.getIpInfo(ip);
        try {
            return objectMapper.readValue(infoRaw, IpInfoModel.class);
        } catch (IOException e) {
            logger.error("查询本机ip时发生异常", e);
            return null;
        }
    }

    /**
     * @Description: 获取客户端IP地址
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
