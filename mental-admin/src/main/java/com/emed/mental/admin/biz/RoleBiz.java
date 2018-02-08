package com.emed.mental.admin.biz;

import com.emed.mental.admin.entity.BaseRole;
import com.emed.mental.admin.mapper.RoleMapper;
import com.emed.mental.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 11:07 2018/2/2 0002
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleBiz extends BaseBiz<RoleMapper,BaseRole> {




}
