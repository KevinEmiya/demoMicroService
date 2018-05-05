package com.ky.demo.integration.io.interceptor;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class ResponseErrorInterceptor extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        if (clientHttpResponse == null) {
            return true;
        }
        return super.hasError(clientHttpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        if(clientHttpResponse != null) {
            super.handleError(clientHttpResponse);
        }
    }
}
