package com.lawcloud.lawper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lawcloud.lawper.common.mapper.CommonMapper;
import com.lawcloud.lawper.model.GroupAuthorities;
import com.lawcloud.lawper.model.Users;

public interface UsersMapper extends CommonMapper<Users> {

    Users getByUsername(@Param("username") String username);
    List<GroupAuthorities> getGroupAuthoritiesByUsername(@Param("username") String username);
}