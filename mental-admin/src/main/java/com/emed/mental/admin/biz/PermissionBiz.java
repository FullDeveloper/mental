package com.emed.mental.admin.biz;

import com.emed.mental.admin.entity.BasePermission;
import com.emed.mental.admin.mapper.PermissionMapper;
import com.emed.mental.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 11:17 2018/2/2 0002
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionBiz extends BaseBiz<PermissionMapper,BasePermission> {


}
