package com.emed.mental.auth.server.service;

/**
 * @Author: 周润斌
 * @Date: create in 上午 15:04 2018/2/1 0001
 * @Description:
 */
public interface AuthService {

    String login(String username, String password) throws Exception;

}
