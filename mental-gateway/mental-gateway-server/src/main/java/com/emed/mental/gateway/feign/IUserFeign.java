package com.emed.mental.gateway.feign;

import com.emed.mental.api.vo.authority.PermissionInfo;
import com.emed.mental.gateway.configuration.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 10:29 2018/2/2 0002
 * @Description:
 */
@FeignClient(value = "mental-admin",configuration = FeignConfiguration.class)
public interface IUserFeign {

    @RequestMapping(value="/api/user/un/{username}/permissions",method = RequestMethod.GET)
    List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username);
    @RequestMapping(value="/api/permissions",method = RequestMethod.GET)
    List<PermissionInfo> getAllPermissionInfo();

}
