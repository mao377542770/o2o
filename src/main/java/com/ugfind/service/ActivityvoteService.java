package com.ugfind.service;

import com.ugfind.model.Activityvote;

public interface ActivityvoteService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Activityvote record);

    Activityvote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activityvote record);
}