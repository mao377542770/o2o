package com.ugfind.service;

import java.util.List;

import com.ugfind.model.PageInfoSearch;
import com.ugfind.model.Resource;
import com.ugfind.model.ResourcePoJo;
import com.ugfind.model.ResourceType;
import com.ugfind.serviceImpl.ResourceFilePoJo;

public interface ResourceService {

	public int insertResource(Resource resource);

	public List<ResourceType> getTypeList();

	public int updateResource(Resource resource);

	public List<Resource> getResourceList(PageInfoSearch pageInfo);

	public void getCountByPage(PageInfoSearch pageInfo);

	public List<Resource> getResourceOrderByDown();

	public ResourcePoJo getResourceInfoById(Integer id);

	public ResourceFilePoJo getDownFile(Integer id);

	public int getResourceTotal();
}