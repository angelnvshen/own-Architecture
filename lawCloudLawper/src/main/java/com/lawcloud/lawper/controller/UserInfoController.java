package com.lawcloud.lawper.controller;

import com.github.pagehelper.PageInfo;
import com.lawcloud.lawper.model.UserInfo;
import com.lawcloud.lawper.model.UserInfoDetail;
import com.lawcloud.lawper.service.UserInfoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dell on 2017/4/7.
 */
@RequestMapping("userInfo")
@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    private Log log = LogFactory.getLog(UserInfoController.class);
    /**
     * 查询userInfo 联表查询
     *
     * @param userInfo 查询条件
     * @param page     页码
     * @param rows     行数
     * @return
     */
    @ResponseBody
    @RequestMapping("selectUserInfo")
    public PageInfo<UserInfoDetail> selectUserInfo(UserInfo userInfo,
                                                   @RequestParam(required = false, defaultValue = "1", value = "page") int page,
                                                   @RequestParam(required = false, defaultValue = "10", value = "rows") int rows) {

        log.info("info log .... ");
        log.warn("warn log .... ");
        log.error("error log .... ");
        return userInfoService.selectUserInfo(userInfo, page, rows);
    }
}
