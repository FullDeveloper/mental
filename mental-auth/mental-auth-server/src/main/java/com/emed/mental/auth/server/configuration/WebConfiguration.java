package com.emed.mental.auth.server.configuration;

import com.emed.mental.auth.server.interceptor.RestServiceAuthInterceptor;
import com.emed.mental.auth.server.interceptor.RestUserAuthInterceptor;
import com.emed.mental.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: 周润斌
 * @Date: create in 上午 16:39 2018/2/1 0001
 * @Description:
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getRestServiceAuthInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        registry.addInterceptor(getRestUserAuthInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        super.addInterceptors(registry);
    }

    @Bean
    public RestServiceAuthInterceptor getRestServiceAuthInterceptor(){
        return new RestServiceAuthInterceptor();
    }

    public RestUserAuthInterceptor getRestUserAuthInterceptor(){
        return new RestUserAuthInterceptor();
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/v2/api-docs",
                "/swagger-resources/**",
                "/client/**",
                "/jwt/**"
        };
        Collections.addAll(list, urls);
        return list;
    }
}
