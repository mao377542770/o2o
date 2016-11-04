package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.EmploymentNewsPage;
import com.ugfind.model.Employmentnews;

public interface EmploymentnewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employmentnews record);

    int insertSelective(Employmentnews record);

    Employmentnews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employmentnews record);

    int updateByPrimaryKeyWithBLOBs(Employmentnews record);

    int updateByPrimaryKey(Employmentnews record);
    
    //获取本校的公告部门列表
    List<Employmentnews> getNewsBypage(EmploymentNewsPage newsPage);
    //更新阅读次数    
    int updateNewsViewCount(Integer id);
    //总条数
    int getSchoolNewsCount(EmploymentNewsPage newsPage);
}