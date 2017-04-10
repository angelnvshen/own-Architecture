package com.lawcloud.lawper.common.base.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的CommonMapper
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
