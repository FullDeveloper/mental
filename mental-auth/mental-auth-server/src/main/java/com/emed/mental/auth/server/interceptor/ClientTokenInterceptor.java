package com.emed.mental.auth.server.interceptor;

import com.emed.mental.auth.server.configuration.ClientConfiguration;
import com.emed.mental.auth.server.service.AuthClientService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 周润斌
 * @Date: create in 上午 15:53 2018/2/1 0001
 * @Description:
 */
public class ClientTokenInterceptor implements RequestInterceptor {

    @Autowired
    private ClientConfiguration clientConfiguration;
    @Autowired
    private AuthClientService authClientService;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            //添加请求头
            requestTemplate.header(clientConfiguration.getClientTokenHeader(),
                    authClientService.apply(clientConfiguration.getClientId(),clientConfiguration.getClientSecret()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
