package com.ugfind.dao;

import java.util.List;
import java.util.Map;

import com.ugfind.model.Deadline;

public interface DeadlineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deadline record);

    int insertSelective(Deadline record);

    Deadline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deadline record);

    int updateByPrimaryKey(Deadline record);
    
    List<Deadline> getMyAllModule(Map<String,Integer> map);
    
    int updateSequence(List<Deadline> list);
}