package com.emed.mental.admin.biz;

import com.emed.mental.admin.entity.BaseUser;
import com.emed.mental.admin.mapper.UserMapper;
import com.emed.mental.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

/**
 * @Author: 周润斌
 * @Date: create in 上午 14:41 2018/2/1 0001
 * @Description:
 */
@Service
public class UserBiz extends BaseBiz<UserMapper,BaseUser> {

    public BaseUser getUserByUsername(String username){
        BaseUser user = new BaseUser();
        user.setUsername(username);
        return mapper.selectOne(user);
    }

}
