/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.lawcloud.lawper.common.service.impl;

import com.lawcloud.lawper.common.service.IService;
import com.lawcloud.lawper.controller.UserInfoController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 基类
 *  共有的增删查改，可以添加其他公共的方法
 *  共有的变量
 */
public abstract class BaseService<T> implements IService<T> {

    /*
     *日志公共变量
     */
    protected Log log = LogFactory.getLog(UserInfoController.class);

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    /**
     * 根据实体对象的主键查询
     * @param key
     * @return
     */
    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 保存实体对象
     * @param entity
     * @return
     */
    public int save(T entity) {
        return mapper.insert(entity);
    }

    /**
     * 根据实体对象主键 删除
     * @param key
     * @return
     */
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    /**
     * 更新实体对象
     * @param entity
     * @return
     */
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 部分更新实体对象，如果对象的某个属性为空，则自动忽略，不更新这个属性。
     * @param entity
     * @return
     */
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * example,类似 hibernate
     * @param example
     * @return
     */
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    //TODO 其他...
}
