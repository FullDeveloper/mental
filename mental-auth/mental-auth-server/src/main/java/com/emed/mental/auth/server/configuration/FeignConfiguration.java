package com.emed.mental.auth.server.configuration;

import com.emed.mental.auth.server.interceptor.ClientTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 周润斌
 * @Date: create in 上午 16:17 2018/2/1 0001
 * @Description:
 */
@Configuration
public class FeignConfiguration {

    @Bean
    ClientTokenInterceptor getClientTokenInterceptor(){
        return new ClientTokenInterceptor();
    }
}
