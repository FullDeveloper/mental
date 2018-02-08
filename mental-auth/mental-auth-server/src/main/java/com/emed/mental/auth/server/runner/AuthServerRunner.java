package com.emed.mental.auth.server.runner;

import com.emed.mental.auth.common.util.jwt.RsaKeyHelper;
import com.emed.mental.auth.server.configuration.KeyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * @Author: 周润斌
 * @Date: create in 上午 15:28 2018/2/1 0001
 * @Description: 项目服务启动的时候加载一些数据或做一些事情
 */
@Configuration
public class AuthServerRunner implements CommandLineRunner{

    private static final String REDIS_USER_PRI_KEY = "MENTAL:AUTH:JWT:PRI"; //用户私钥
    private static final String REDIS_USER_PUB_KEY = "MENTAL:AUTH:JWT:PUB"; //用户公钥
    private static final String REDIS_SERVICE_PRI_KEY = "MENTAL:AUTH:CLIENT:PRI"; //redis服务私钥
    private static final String REDIS_SERVICE_PUB_KEY = "MENTAL:AUTH:CLIENT:PUB"; //redis服务公钥

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... strings) throws Exception {
        if(redisTemplate.hasKey(REDIS_USER_PRI_KEY) && redisTemplate.hasKey(REDIS_USER_PUB_KEY)
                && redisTemplate.hasKey(REDIS_SERVICE_PRI_KEY) && redisTemplate.hasKey(REDIS_SERVICE_PUB_KEY)){
            keyConfiguration.setUserPriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PRI_KEY).toString()));
            keyConfiguration.setUserPubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PUB_KEY).toString()));
            keyConfiguration.setServicePriKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PRI_KEY).toString()));
            keyConfiguration.setServicePubKey(RsaKeyHelper.toBytes(redisTemplate.opsForValue().get(REDIS_SERVICE_PUB_KEY).toString()));
        }else{
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(keyConfiguration.getUserSecret());
            keyConfiguration.setUserPriKey(keyMap.get("pri"));
            keyConfiguration.setUserPubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_USER_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_USER_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
            keyMap = RsaKeyHelper.generateKey(keyConfiguration.getServiceSecret());
            keyConfiguration.setServicePriKey(keyMap.get("pri"));
            keyConfiguration.setServicePubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_SERVICE_PRI_KEY, RsaKeyHelper.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_SERVICE_PUB_KEY, RsaKeyHelper.toHexString(keyMap.get("pub")));
        }
    }
}
