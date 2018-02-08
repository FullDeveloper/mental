package com.emed.mental.auth.server.feign;

import com.emed.mental.api.vo.user.UserInfo;
import com.emed.mental.auth.server.configuration.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 周润斌
 * @Date: create in 上午 15:05 2018/2/1 0001
 * @Description:
 */
@FeignClient(value = "mental-admin" ,configuration = FeignConfiguration.class)
public interface IUserService {

    @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
    UserInfo validate(@RequestParam("username") String username, @RequestParam("password") String password);

}
