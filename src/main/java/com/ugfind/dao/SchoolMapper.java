package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.School;

public interface SchoolMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);

	List<School> getSchoolListByKeyWord(String keyWord);

	List<School> getSchoolListByCity(String city);
}