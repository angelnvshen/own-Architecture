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
     * @param country
     * @param page
     * @param rows
     * @return
     */
    List<Country> selectByCountry(Country country, int page, int rows);

}
