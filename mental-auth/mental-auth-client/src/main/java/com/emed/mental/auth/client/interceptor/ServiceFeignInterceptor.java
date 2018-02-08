package com.emed.mental.auth.client.interceptor;

import com.emed.mental.auth.client.config.ServiceAuthConfig;
import com.emed.mental.auth.client.config.UserAuthConfig;
import com.emed.mental.auth.client.jwt.ServiceAuthUtil;
import com.emed.mental.common.context.BaseContextHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 周润斌
 * @Date: create in 上午 9:34 2018/2/2 0002
 * @Description:
 */
public class ServiceFeignInterceptor implements RequestInterceptor {

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private ServiceAuthUtil serviceAuthUtil;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //服务token头
        requestTemplate.header(serviceAuthConfig.getTokenHeader(), serviceAuthUtil.getClientToken());
        //用户token头
        requestTemplate.header(userAuthConfig.getTokenHeader(), BaseContextHandler.getToken());
    }
}
