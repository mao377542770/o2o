package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.ResourceType;

public interface ResourceTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceType record);

    int insertSelective(ResourceType record);

    ResourceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceType record);

    int updateByPrimaryKey(ResourceType record);


	List<ResourceType> getTypeList();

}