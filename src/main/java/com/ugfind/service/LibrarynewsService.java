package com.ugfind.service;

import java.util.List;

import com.ugfind.model.Librarynews;
import com.ugfind.model.NewsPageConfig;

public interface LibrarynewsService {
	int deleteByPrimaryKey(Integer id);

    int insert(Librarynews record);

    int insertSelective(Librarynews record);

    Librarynews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Librarynews record);

    int updateByPrimaryKeyWithBLOBs(Librarynews record);

    int updateByPrimaryKey(Librarynews record);
    
    int getSchoolNewsCount(NewsPageConfig newsPage);
    
    List<Librarynews> getNewsBypage(NewsPageConfig newsPage);

	Librarynews getNewsInfo(Integer newsId);
	
	/**
	 * 修改阅读次数
	 * @param id
	 * @return
	 */
	int updateLibraryNewsViewCount(Integer id);
}
