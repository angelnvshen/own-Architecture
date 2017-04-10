package com.lawcloud.lawper.service;

import com.github.pagehelper.PageInfo;
import com.lawcloud.lawper.common.service.IService;
import com.lawcloud.lawper.model.UserInfo;
import com.lawcloud.lawper.model.UserInfoDetail;

/**
 * UserInfo 的业务增删查改
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 联表查询
     *
     * @param userInfo 查询实体
     * @param page     页码
     * @param rows     行数
     * @return 返回对象集合和页码信息
     */
    PageInfo<UserInfoDetail> selectUserInfo(UserInfo userInfo, int page, int rows);

}
