package com.ugfind.dao;

import com.ugfind.model.UserResourceRl;

public interface UserResourceRlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserResourceRl record);

    int insertSelective(UserResourceRl record);

    UserResourceRl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserResourceRl record);

    int updateByPrimaryKey(UserResourceRl record);

	int addTotal(Integer userId);
}