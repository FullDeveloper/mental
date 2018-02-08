package com.emed.mental.admin;

import com.emed.mental.auth.client.EnableAuthClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Author: 周润斌
 * @Date: create in 上午 12:52 2018/2/1 0001
 * @Description:
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients({"com.emed.mental.admin","com.emed.mental.auth.client.feign"})
@EnableAuthClient
@MapperScan(basePackages = "com.emed.mental.admin.mapper")
public class AdminBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootstrap.class,args);
    }

}
