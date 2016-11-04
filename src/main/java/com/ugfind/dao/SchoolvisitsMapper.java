package com.ugfind.dao;

import com.ugfind.model.Schoolvisits;

public interface SchoolvisitsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Schoolvisits record);

    int insertSelective(Schoolvisits record);

    Schoolvisits selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Schoolvisits record);

    int updateByPrimaryKey(Schoolvisits record);
    
    Schoolvisits selectSchoolVisitsBySchoolId(Integer schoolId);

	int addSchoolVisits(Integer schoolId);
}