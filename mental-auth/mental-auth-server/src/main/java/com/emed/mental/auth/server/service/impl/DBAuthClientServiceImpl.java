package com.emed.mental.auth.server.service.impl;

import com.emed.mental.auth.server.bean.ClientInfo;
import com.emed.mental.auth.server.entity.AuthClient;
import com.emed.mental.auth.server.mapper.ClientMapper;
import com.emed.mental.auth.server.service.AuthClientService;
import com.emed.mental.auth.server.util.client.ClientTokenUtil;
import com.emed.mental.common.exception.auth.ClientInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 15:58 2018/2/1 0001
 * @Description:
 */
@Service
public class DBAuthClientServiceImpl implements AuthClientService{
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private ClientTokenUtil clientTokenUtil;

    @Override
    public String apply(String clientId, String secret) throws Exception {
        AuthClient client = getClient(clientId,secret);
        return clientTokenUtil.generateToken(new ClientInfo(client.getCode(),client.getName(),client.getId().toString()));
    }

    @Override
    public List<String> getAllowedClient(String clientId) {
        AuthClient info = getClient(clientId);
        List<String> clients = clientMapper.selectAllowedClient(info.getId() + "");
        if(clients==null) {
            new ArrayList<String>();
        }
        return clients;
    }

    @Override
    public List<String> getAllowedClient(String serviceId, String secret) {
        AuthClient info = this.getClient(serviceId, secret);
        List<String> clients = clientMapper.selectAllowedClient(info.getId() + "");
        if(clients==null) {
            new ArrayList<String>();
        }
        return clients;
    }

    @Override
    public void validate(String clientId, String secret) throws Exception {
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = clientMapper.selectOne(client);
        if(client==null||!client.getSecret().equals(secret)){
            throw new ClientInvalidException("Client not found or Client secret is error!");
        }
    }

    private AuthClient getClient(String clientId) {
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = clientMapper.selectOne(client);
        return client;
    }

    private AuthClient getClient(String clientId, String secret) {
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = clientMapper.selectOne(client);
        if(client == null || !client.getSecret().equals(secret)){
            throw new ClientInvalidException("Client not found or Client secret is error!");
        }
        return client;
    }
}
