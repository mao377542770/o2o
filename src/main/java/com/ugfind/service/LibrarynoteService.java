package com.ugfind.service;

import java.util.Map;

import com.ugfind.model.Librarynote;

public interface LibrarynoteService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Librarynote record);

    Librarynote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Librarynote record);
    
    Librarynote getNoteBydeptAndSchool(Map<String, Integer> map);
}