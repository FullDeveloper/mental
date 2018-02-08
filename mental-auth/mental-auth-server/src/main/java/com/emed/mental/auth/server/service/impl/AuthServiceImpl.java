package com.emed.mental.auth.server.service.impl;

import com.emed.mental.api.vo.user.UserInfo;
import com.emed.mental.auth.common.util.jwt.JWTInfo;
import com.emed.mental.auth.server.feign.IUserService;
import com.emed.mental.auth.server.service.AuthService;
import com.emed.mental.auth.server.util.user.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author: 周润斌
 * @Date: create in 上午 15:05 2018/2/1 0001
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private IUserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) throws Exception {
        UserInfo info = userService.validate(username,password);
        String token = "";
        if (!StringUtils.isEmpty(info.getId())) {
            token = jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        return token;
    }
}
