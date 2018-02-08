package com.emed.mental.auth.server.mapper;

import com.emed.mental.auth.server.entity.AuthClient;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 16:07 2018/2/1 0001
 * @Description:
 */
public interface ClientMapper extends Mapper<AuthClient> {

    List<String> selectAllowedClient(String clientId);

}
