package com.lawcloud.lawper.service;

import com.lawcloud.lawper.common.service.IService;
import com.lawcloud.lawper.model.Country;

import java.util.List;

/**
 * country 的业务增删查改
 */
public interface CountryService extends IService<Country> {

    /**
     * 根据条件分页查询
     *
     * @param country 查询实体
     * @param page    页码
     * @param rows    行数
     * @return 返回对象集合和页码信息
     */
    List<Country> selectByCountry(Country country, int page, int rows);

}
