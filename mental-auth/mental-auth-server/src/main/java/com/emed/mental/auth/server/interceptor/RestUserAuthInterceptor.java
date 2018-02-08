package com.emed.mental.auth.server.interceptor;

import com.emed.mental.auth.common.util.jwt.IJWTInfo;
import com.emed.mental.auth.server.configuration.UserConfiguration;
import com.emed.mental.auth.server.util.user.JwtTokenUtil;
import com.emed.mental.common.context.BaseContextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 周润斌
 * @Date: create in 上午 17:05 2018/2/1 0001
 * @Description:
 */
public class RestUserAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserConfiguration userConfiguration;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String token = request.getHeader(userConfiguration.getUserTokenHeader());
        IJWTInfo infoFromToken = jwtTokenUtil.getInfoFromToken(token);
        BaseContextHandler.setUsername(infoFromToken.getUniqueName());
        BaseContextHandler.setName(infoFromToken.getName());
        BaseContextHandler.setUserID(infoFromToken.getId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
