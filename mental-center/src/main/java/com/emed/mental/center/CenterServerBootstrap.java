package com.emed.mental.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: 周润斌
 * @Date: create in 上午 12:43 2018/2/1 0001
 * @Description:
 */

@EnableEurekaServer
@SpringBootApplication
public class CenterServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(CenterServerBootstrap.class,args);
    }

}
