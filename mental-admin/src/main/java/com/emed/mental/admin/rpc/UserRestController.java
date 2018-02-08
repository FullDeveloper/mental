package com.emed.mental.admin.rpc;

import com.emed.mental.admin.rpc.service.PermissionService;
import com.emed.mental.api.vo.authority.PermissionInfo;
import com.emed.mental.api.vo.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 13:12 2018/2/1 0001
 * @Description:
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/user/validate" ,method = RequestMethod.POST)
    public UserInfo validate(String username,String password){

        return permissionService.validate(username,password);
    }

    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public List<PermissionInfo> getAllPermission(){
        return permissionService.getAllPermission();
    }

    @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
    public @ResponseBody
    List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username){
        return permissionService.getPermissionByUsername(username);
    }

}
