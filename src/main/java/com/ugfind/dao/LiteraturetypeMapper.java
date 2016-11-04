package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.Literaturetype;

public interface LiteraturetypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Literaturetype record);

    int insertSelective(Literaturetype record);

    Literaturetype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Literaturetype record);

    int updateByPrimaryKey(Literaturetype record);
    
    List<Literaturetype> getAllType(Integer schoolId);
}