package com.emed.mental.auth.server.interceptor;

import com.emed.mental.auth.common.util.jwt.IJWTInfo;
import com.emed.mental.auth.server.configuration.ClientConfiguration;
import com.emed.mental.auth.server.service.AuthClientService;
import com.emed.mental.auth.server.util.client.ClientTokenUtil;
import com.emed.mental.common.exception.auth.ClientForbiddenException;
import com.sun.deploy.config.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 周润斌
 * @Date: create in 上午 16:24 2018/2/1 0001
 * @Description:
 */
public class RestServiceAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ClientConfiguration clientConfiguration;
    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Autowired
    private AuthClientService authClientService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取执行的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //通过请求头获取令牌
        String token = request.getHeader(clientConfiguration.getClientTokenHeader());
        //获取令牌的信息
        IJWTInfo infoFromToken = clientTokenUtil.getInfoFromToken(token);
        String uniqueName = infoFromToken.getUniqueName();
        //查看是否存在允许的服务中
        for(String client: authClientService.getAllowedClient(clientConfiguration.getClientId())){
            if(client.equals(uniqueName)){
                return super.preHandle(request, response, handler);
            }
        }
        throw new ClientForbiddenException("Client is Forbidden!");
    }
}
