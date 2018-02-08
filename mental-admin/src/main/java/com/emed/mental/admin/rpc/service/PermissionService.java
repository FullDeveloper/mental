package com.emed.mental.admin.rpc.service;

import com.emed.mental.admin.biz.UserBiz;
import com.emed.mental.admin.constant.AdminCommonConstant;
import com.emed.mental.admin.entity.BaseUser;
import com.emed.mental.api.vo.authority.PermissionInfo;
import com.emed.mental.api.vo.user.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 13:22 2018/2/1 0001
 * @Description:
 */
@Service
public class PermissionService {

    @Autowired
    private UserBiz userBiz;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserInfo validate(String username, String password) {
        UserInfo userInfo = new UserInfo();
        BaseUser user = userBiz.getUserByUsername(username);
        if (encoder.matches(password, user.getPassword())) {
            BeanUtils.copyProperties(user, user);
            userInfo.setId(user.getId().toString());
        }
        return userInfo;
    }

    public List<PermissionInfo> getPermissionByUsername(String username) {
        BaseUser user = userBiz.getUserByUsername(username);
       /* List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menuToPermission(menus, result);
        List<Element> elements = elementBiz.getAuthorityElementByUserId(user.getId() + "");
        elementToPermission(result, elements);*/
        return null;
    }

    public List<PermissionInfo> getAllPermission() {
        List<PermissionInfo> resultList = new ArrayList<>();
      /*  //获取所有的菜单信息
        List<Menu> menuList = menuBiz.selectListAll();
        menuToPermission( menuList, resultList);
        //拿到所有元素信息
        List<Element> elements = elementBiz.selectListAll();
        elementToPermission(resultList, elements);*/
        return resultList;
    }

    /*private void elementToPermission(List<PermissionInfo> resultList, List<Element> elements) {
        PermissionInfo info;
        for (Element element : elements) {
            info = new PermissionInfo();
            info.setCode(element.getCode());
            info.setType(element.getType());
            info.setUri(element.getUri());
            info.setMethod(element.getMethod());
            info.setName(element.getName());
            info.setMenu(element.getMenuId());
            resultList.add(info);
        }
    }*/

    /*private void menuToPermission(List<Menu> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for (Menu menu : menus) {
            //如果菜单的链接地址为空
            if(StringUtils.isBlank(menu.getHref())){
                menu.setHref("/" + menu.getCode());
            }
            info = new PermissionInfo();
            info.setCode(menu.getCode());
            info.setType(AdminCommonConstant.RESOURCE_TYPE_MENU);
            info.setName(AdminCommonConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            info.setUri(uri);
            info.setMethod(AdminCommonConstant.RESOURCE_REQUEST_METHOD_GET);
            info.setMenu(menu.getTitle());
            result.add(info);
        }
    }*/

}
