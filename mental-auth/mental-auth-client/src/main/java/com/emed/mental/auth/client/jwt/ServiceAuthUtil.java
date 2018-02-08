package com.emed.mental.auth.client.jwt;

import com.emed.mental.auth.client.config.ServiceAuthConfig;
import com.emed.mental.auth.client.feign.ServiceAuthFeign;
import com.emed.mental.auth.common.event.AuthRemoteEvent;
import com.emed.mental.auth.common.util.jwt.IJWTInfo;
import com.emed.mental.auth.common.util.jwt.JWTHelper;
import com.emed.mental.common.exception.auth.ClientTokenException;
import com.emed.mental.common.result.BaseResponse;
import com.emed.mental.common.result.ObjectRestResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 9:36 2018/2/2 0002
 * @Description:
 */
@Configuration
@EnableScheduling
public class ServiceAuthUtil{

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private ServiceAuthFeign serviceAuthFeign;
    private Logger logger = LoggerFactory.getLogger(ServiceAuthUtil.class);
    private List<String> allowedClient;
    private String clientToken;

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        try {
            return JWTHelper.getInfoFromToken(token, serviceAuthConfig.getPubKeyByte());
        } catch (ExpiredJwtException ex) {
            throw new ClientTokenException("Client token expired!");
        } catch (SignatureException ex) {
            throw new ClientTokenException("Client token signature error!");
        } catch (IllegalArgumentException ex) {
            throw new ClientTokenException("Client token is null or empty!");
        }
    }

    public List<String> getAllowedClient() {
        if (this.allowedClient == null) {
            this.refreshAllowedClient();
        }
        return allowedClient;
    }

    /**
     * 刷新允许访问的客户端
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAllowedClient(){
        logger.debug("refresh allowed client ......");
        BaseResponse resp = serviceAuthFeign.getAllowedClient(serviceAuthConfig.getClientId(),serviceAuthConfig.getClientSecret());
        if(resp.getStatus() == 200){
            ObjectRestResponse<List<String>> allowedClient = (ObjectRestResponse<List<String>>) resp;
            this.allowedClient = allowedClient.getData();
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshClientToken() {
        logger.debug("refresh client token.....");
        BaseResponse resp = serviceAuthFeign.getAccessToken(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getStatus() == 200) {
            ObjectRestResponse<String> clientToken = (ObjectRestResponse<String>) resp;
            this.clientToken = clientToken.getData();
        }
    }

    public String getClientToken() {
        if (this.clientToken == null) {
            this.refreshClientToken();
        }
        return clientToken;
    }


}
