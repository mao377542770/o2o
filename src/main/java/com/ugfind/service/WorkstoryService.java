package com.ugfind.service;

import java.util.List;

import com.ugfind.model.NewsPageConfig;
import com.ugfind.model.Workstory;

public interface WorkstoryService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Workstory record);

    Workstory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Workstory record);
    
    //获取本校职场故事数量
    int getWorkstoryCount(NewsPageConfig newsPage);
    //分页获取职场故事
    List<Workstory> getWorkstoryBypage(NewsPageConfig newsPage);
    //阅读数修改
    int updateWorkstoryViewCount(Integer id);
}