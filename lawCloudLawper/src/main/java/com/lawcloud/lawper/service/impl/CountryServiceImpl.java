package com.lawcloud.lawper.service.impl;

import com.github.pagehelper.PageHelper;
import com.lawcloud.lawper.common.service.impl.BaseService;
import com.lawcloud.lawper.model.Country;
import com.lawcloud.lawper.service.CountryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * country 的业务增删查改
 */
@Service("countryService")
public class CountryServiceImpl extends BaseService<Country> implements CountryService {

    /**
     * 查询country信息
     *
     * @param country 查询实体
     * @param page    页码
     * @param rows    行数
     * @return
     */
    @Override
    public List<Country> selectByCountry(Country country, int page, int rows) {
        Example example = new Example(Country.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(country.getCountryName())) {
            criteria.andLike("countryName", "%" + country.getCountryName() + "%");
        }
        if (StringUtil.isNotEmpty(country.getCountryCode())) {
            criteria.andLike("countryCode", "%" + country.getCountryCode() + "%");
        }
        if (country.getId() != null) {
            criteria.andEqualTo("id", country.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }

}
