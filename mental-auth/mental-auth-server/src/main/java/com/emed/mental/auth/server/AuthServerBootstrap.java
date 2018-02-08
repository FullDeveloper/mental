package com.emed.mental.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Author: 周润斌
 * @Date: create in 上午 12:56 2018/2/1 0001
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@MapperScan("com.emed.mental.auth.server.mapper")
public class AuthServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerBootstrap.class,args);
    }

}
