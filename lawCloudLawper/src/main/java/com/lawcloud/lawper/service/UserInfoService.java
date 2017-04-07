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
     * @param userInfo
     * @param page
     * @param rows
     * @return
     */
    PageInfo<UserInfoDetail> selectUserInfo(UserInfo userInfo, int page, int rows);

}
