package com.emed.mental.auth.client.runner;

import com.emed.mental.auth.client.config.ServiceAuthConfig;
import com.emed.mental.auth.client.config.UserAuthConfig;
import com.emed.mental.auth.client.feign.ServiceAuthFeign;
import com.emed.mental.common.result.BaseResponse;
import com.emed.mental.common.result.ObjectRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * 监听完成时触发
 *
 */
@Configuration
public class AuthClientRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(AuthClientRunner.class);

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private ServiceAuthFeign serviceAuthFeign;

    @Override
    public void run(String... args) throws Exception {
        logger.info("初始化加载用户pubKey");
        BaseResponse resp = serviceAuthFeign.getUserPublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getStatus() == 200) {
            ObjectRestResponse<byte[]> userResponse = (ObjectRestResponse<byte[]>) resp;
            this.userAuthConfig.setPubKeyByte(userResponse.getData());
        }
        logger.info("初始化加载客户pubKey");
        resp = serviceAuthFeign.getServicePublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getStatus() == 200) {
            ObjectRestResponse<byte[]> userResponse = (ObjectRestResponse<byte[]>) resp;
            this.serviceAuthConfig.setPubKeyByte(userResponse.getData());
        }
    }
}