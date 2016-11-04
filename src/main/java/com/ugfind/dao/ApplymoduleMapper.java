package com.ugfind.dao;

import com.ugfind.model.Applymodule;

public interface ApplymoduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Applymodule record);

    int insertSelective(Applymodule record);

    Applymodule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Applymodule record);

    int updateByPrimaryKey(Applymodule record);
}