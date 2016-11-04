package com.ugfind.service;

import com.ugfind.model.Applymodule;

public interface ApplymoduleService {
    int deleteByPrimaryKey(Integer id);

    int insert(Applymodule record);

    int insertSelective(Applymodule record);

    Applymodule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Applymodule record);

    int updateByPrimaryKey(Applymodule record);
}