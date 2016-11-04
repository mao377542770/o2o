package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.Voteoption;

public interface VoteoptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Voteoption record);

    int insertSelective(Voteoption record);

    Voteoption selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Voteoption record);

    int updateByPrimaryKey(Voteoption record);
    
    //根据投票公告id获取它的投票项
    List<Voteoption> selectOptionsByActivityId(Integer activityId);
}