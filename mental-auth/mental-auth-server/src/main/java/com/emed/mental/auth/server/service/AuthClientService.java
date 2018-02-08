package com.emed.mental.auth.server.service;

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 15:57 2018/2/1 0001
 * @Description:
 */
public interface AuthClientService {

    String apply(String clientId, String secret) throws Exception;

    List<String> getAllowedClient(String clientId);

    /**
     * 获取授权的客户端列表
     * @param serviceId
     * @param secret
     * @return
     */
    public List<String> getAllowedClient(String serviceId, String secret);

    void validate(String clientId, String secret) throws Exception;
}
