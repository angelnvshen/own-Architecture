package com.lawcloud.lawper.common.controller;

import com.lawcloud.lawper.controller.UserInfoController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * controller 层的基类，里面放置controller层公共的变量，或者方法
 * Created by dell on 2017/4/10.
 */
public class BashController {
    /*
     *日志公共变量
     */
    protected Log log = LogFactory.getLog(UserInfoController.class);
}
