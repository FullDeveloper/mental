package com.emed.mental.gateway.configuration;

import com.emed.mental.auth.client.interceptor.ServiceFeignInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @Author: 周润斌
 * @Date: create in 上午 9:32 2018/2/2 0002
 * @Description:
 */
public class FeignConfiguration {

    @Bean
    ServiceFeignInterceptor getServiceFeignInterceptor(){
        return new ServiceFeignInterceptor();
    }

}
