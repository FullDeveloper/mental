package com.emed.mental.admin.mapper;

import com.emed.mental.admin.entity.BasePermission;
import com.emed.mental.admin.util.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 11:15 2018/2/2 0002
 * @Description:
 */
public interface PermissionMapper extends CommonMapper<BasePermission> {

    /*public List<Element> selectAuthorityElementByUserId(@Param("userId")String userId);*/

}
