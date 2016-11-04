package com.ugfind.service;

import java.util.List;

import com.ugfind.model.EmploymentNewsPage;
import com.ugfind.model.Employmentnews;

public interface EmploymentnewsService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Employmentnews record);

    Employmentnews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employmentnews record);
    
    //获取本校的公告部门列表
    List<Employmentnews> getNewsBypage(EmploymentNewsPage newsPage);
    //更新阅读次数    
    int updateNewsViewCount(Integer id);
    //总条数
    int getSchoolNewsCount(EmploymentNewsPage newsPage);
}