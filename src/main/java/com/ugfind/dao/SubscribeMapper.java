package com.ugfind.dao;

import com.ugfind.model.Subscribe;

public interface SubscribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    Subscribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);
}