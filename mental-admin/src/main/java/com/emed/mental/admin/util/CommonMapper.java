package com.emed.mental.admin.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: 周润斌
 * @Date: create in 上午 14:24 2018/2/1 0001
 * @Description:
 */
public interface CommonMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
