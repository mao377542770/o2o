package com.ugfind.service;

import java.util.List;

import com.ugfind.model.Voteoption;

public interface VoteoptionService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Voteoption record);

    Voteoption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Voteoption record);
    
    //根据投票公告id获取它的投票项
    List<Voteoption> selectOptionsByActivityId(Integer activityId);
}