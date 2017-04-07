package com.lawcloud.lawper.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lawcloud.lawper.common.service.impl.BaseService;
import com.lawcloud.lawper.dao.UserInfoDao;
import com.lawcloud.lawper.model.Country;
import com.lawcloud.lawper.model.UserInfo;
import com.lawcloud.lawper.model.UserInfoDetail;
import com.lawcloud.lawper.service.CountryService;
import com.lawcloud.lawper.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * UserInfo 的业务增删查改
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseService<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public PageInfo<UserInfoDetail> selectUserInfo(UserInfo userInfo, int page, int rows) {
        //分页查询
        PageHelper.startPage(page, rows);

        List<UserInfoDetail> list = userInfoDao.selectUserInfo(userInfo);

        PageInfo<UserInfoDetail> pageInfo = new PageInfo<UserInfoDetail>(list);

        return pageInfo;
    }
}
