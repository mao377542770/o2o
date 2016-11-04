package com.ugfind.service;

import java.util.List;
import java.util.Map;

import com.ugfind.model.Rules;

public interface RulesService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Rules record);

    Rules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rules record);
    
    List<Rules> getRules(Map<String, Integer> map);
}