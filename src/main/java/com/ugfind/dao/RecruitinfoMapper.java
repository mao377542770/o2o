package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.NewsPageConfig;
import com.ugfind.model.Recruitinfo;

public interface RecruitinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recruitinfo record);

    int insertSelective(Recruitinfo record);

    Recruitinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Recruitinfo record);

    int updateByPrimaryKeyWithBLOBs(Recruitinfo record);

    int updateByPrimaryKey(Recruitinfo record);
    
    //获取本校招聘资讯数量
    int getRecruitInfoCount(NewsPageConfig newsPage);
    //分页获取招聘资讯
    List<Recruitinfo> getRecruitInfoBypage(NewsPageConfig newsPage);
    //阅读数修改
    int updateRecruitInfoViewCount(Integer id);
}