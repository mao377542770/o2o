package com.ugfind.dao;

import java.util.List;

import com.ugfind.model.PageInfo;
import com.ugfind.model.PageInfoSearch;
import com.ugfind.model.Resource;
import com.ugfind.model.ResourcePoJo;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKeyWithBLOBs(Resource record);

    int updateByPrimaryKey(Resource record);

	List<Resource> getResourceList(PageInfoSearch pageInfo);

	Integer getCountByPage(PageInfoSearch pageInfo);

	List<Resource> getResourceOrderByDown();
	
	ResourcePoJo getResourceInfoById(Integer id);
	
	Resource getDownFile(Integer id);
	
	int getResourceTotal();
}