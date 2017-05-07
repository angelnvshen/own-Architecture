package com.lawcloud.lawper.controller;

import com.github.pagehelper.PageInfo;
import com.lawcloud.lawper.common.base.controller.BashController;
import com.lawcloud.lawper.common.util.AppCodeConstantUtil;
import com.lawcloud.lawper.common.util.analyzer.MyIkAnalyzer;
import com.lawcloud.lawper.model.UserInfo;
import com.lawcloud.lawper.model.UserInfoDetail;
import com.lawcloud.lawper.service.UserInfoService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by dell on 2017/4/7.
 */
@RequestMapping("userInfo")
@Controller
public class UserInfoController extends BashController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCodeConstantUtil util;

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

        return userInfoService.selectUserInfo(userInfo, page, rows);
    }

    /**
     * restTemplate 调用测试
     *
     * @param userInfo {
     *                 "username":"test1"
     *                 }
     * @return {
     * "pageNum": 1,
     * "pageSize": 10,
     * "list": [
     * {
     * "id": 1,
     * "username": "test1",
     * "password": "12345678",
     * "usertype": "1",
     * ....
     * "countryName": "Angola",
     * "cityName": "石家庄"
     * }
     * ],
     * ....
     * }
     * @throws URISyntaxException
     */
    @ResponseBody
    @RequestMapping("restTemplateTest")
    public PageInfo<UserInfoDetail> restTemplateTest(@RequestBody UserInfo userInfo) throws URISyntaxException {

        String url = "http://localhost:8888/lawCloudLawper/userInfo/selectUserInfo";
        RequestEntity<UserInfo> requestEntity = new RequestEntity<UserInfo>(userInfo, HttpMethod.POST, new URI(url));

        ParameterizedTypeReference<PageInfo<UserInfoDetail>> parameterizedTypeReference = new ParameterizedTypeReference<PageInfo<UserInfoDetail>>() {
        };

        ResponseEntity<PageInfo<UserInfoDetail>> result = restTemplate.exchange(requestEntity, parameterizedTypeReference);

        System.out.println(result.getStatusCode());
        return result.getBody();
    }

    /**
     * 测试读取配置文件属性
     *
     * @param key key
     * @return value
     */
    @ResponseBody
    @RequestMapping("utilTest")
    public String getValueFromPropertiesFile(@RequestParam(defaultValue = "hello") String key) {
        String value = util.getProperty(key);
        return " [key] " + key + " : " + "[value] " + value;
    }

    @ResponseBody
    @RequestMapping("testLucene")
    public String testLucene() throws IOException {
        return "parse indexStr";
    }
}
