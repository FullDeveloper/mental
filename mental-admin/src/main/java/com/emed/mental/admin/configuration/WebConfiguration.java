package com.emed.mental.admin.configuration;

import com.emed.mental.auth.client.interceptor.RestServiceInterceptor;
import com.emed.mental.auth.client.interceptor.RestUserAuthInterceptor;
import com.emed.mental.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: 周润斌
 * @Date: create in 上午 13:52 2018/2/2 0002
 * @Description:
 */
@Configuration("adminWebConfig")
@Primary
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getRestServiceInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        commonPathPatterns.add("/api/user/validate");
        registry.addInterceptor(getRestUserAuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        super.addInterceptors(registry);
    }

    @Bean
    RestServiceInterceptor getRestServiceInterceptor() {
        return new RestServiceInterceptor();
    }

    @Bean
    RestUserAuthInterceptor getRestUserAuthInterceptor() {
        return new RestUserAuthInterceptor();
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/v2/api-docs",
                "/swagger-resources/**",
                "/cache/**",
                "/api/log/save"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
