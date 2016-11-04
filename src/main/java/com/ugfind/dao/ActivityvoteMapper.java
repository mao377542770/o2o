package com.ugfind.dao;

import com.ugfind.model.Activityvote;

public interface ActivityvoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activityvote record);

    int insertSelective(Activityvote record);

    Activityvote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activityvote record);

    int updateByPrimaryKey(Activityvote record);
}