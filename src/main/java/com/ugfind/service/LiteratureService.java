package com.ugfind.service;

import java.util.List;

import com.ugfind.model.Literature;
import com.ugfind.model.LiteratureConfig;

public interface LiteratureService {
    int deleteByPrimaryKey(Integer id);

    int insert(Literature record);

    int insertSelective(Literature record);

    Literature selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Literature record);

    int updateByPrimaryKeyWithBLOBs(Literature record);

    int updateByPrimaryKey(Literature record);
    
    List<Literature> getAllLiterature(LiteratureConfig liteCon);
    
    int getLiteratureCount(LiteratureConfig liteCon);
}