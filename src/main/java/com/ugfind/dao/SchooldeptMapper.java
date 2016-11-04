package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.SchoolServiceInfo;
import com.ugfind.model.Schooldept;

public interface SchooldeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Schooldept record);

    int insertSelective(Schooldept record);

    Schooldept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Schooldept record);

    int updateByPrimaryKey(Schooldept record);
    
    SchoolServiceInfo getSchoolServiceInfoByUserId(Integer id);

	List<Schooldept> getDeptBySchoolId(Integer schoolId);
	
	//查看学校是否开通了办公区
	int countOfDept(Integer schoolId);
}