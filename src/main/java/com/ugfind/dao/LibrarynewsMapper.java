package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.Librarynews;
import com.ugfind.model.NewsPageConfig;

public interface LibrarynewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Librarynews record);

    int insertSelective(Librarynews record);

    Librarynews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Librarynews record);

    int updateByPrimaryKeyWithBLOBs(Librarynews record);

    int updateByPrimaryKey(Librarynews record);
    
    int getSchoolNewsCount(NewsPageConfig newsPage);
    
    List<Librarynews> getNewsBypage(NewsPageConfig newsPage);
    
    int updateLibraryNewsViewCount(Integer id);
}