package com.lawcloud.lawper.dao;

import com.lawcloud.lawper.common.mapper.CommonMapper;
import com.lawcloud.lawper.model.UrlResources;

import java.util.List;
import java.util.Map;

public interface UrlResourcesMapper extends CommonMapper<UrlResources> {

    List<Map<String,String>> getURLResourceMapping();
}