package com.ugfind.dao;

import java.util.List;
import java.util.Map;

import com.ugfind.model.Rules;

public interface RulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rules record);

    int insertSelective(Rules record);

    Rules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rules record);

    int updateByPrimaryKeyWithBLOBs(Rules record);

    int updateByPrimaryKey(Rules record);
    
    List<Rules> getRules(Map<String, Integer> map);
}