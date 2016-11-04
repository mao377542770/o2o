package com.ugfind.dao;

import java.util.Map;

import com.ugfind.model.Librarynote;

public interface LibrarynoteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Librarynote record);

    int insertSelective(Librarynote record);

    Librarynote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Librarynote record);

    int updateByPrimaryKeyWithBLOBs(Librarynote record);

    int updateByPrimaryKey(Librarynote record);
    
    Librarynote getNoteBydeptAndSchool(Map<String, Integer> map);
}