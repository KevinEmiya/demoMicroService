package com.ky.demo.integration.io.interceptor;

import com.ky.demo.integration.io.MsgHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RequestLogInterceptor implements ClientHttpRequestInterceptor {

    private static final Log LOG = LogFactory.getLog(MsgHandler.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        ClientHttpResponse response = null;
        LOG.info("正在执行请求：" + httpRequest.getURI());
        try{
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }
        catch (Exception e)
        {
            LOG.error("执行请求时发生异常：" + e.getMessage());
        }
        return null;
    }
}
