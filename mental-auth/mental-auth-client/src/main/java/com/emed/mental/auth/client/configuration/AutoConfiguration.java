package com.emed.mental.auth.client.configuration;

import com.emed.mental.auth.client.config.ServiceAuthConfig;
import com.emed.mental.auth.client.config.UserAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 周润斌
 * @Date: create in 上午 18:11 2018/2/1 0001
 * @Description:
 */
@Configuration
@ComponentScan({"com.emed.mental.auth.client"})
public class AutoConfiguration {

    @Bean
    ServiceAuthConfig getServiceAuthConfig(){
        return new ServiceAuthConfig();
    }

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }


}
