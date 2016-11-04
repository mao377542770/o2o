package com.ugfind.service;

import java.util.List;

import com.ugfind.model.Module;

public interface ModuleService {
    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
    
    List<Module> getAllModule(Integer deptId);
}