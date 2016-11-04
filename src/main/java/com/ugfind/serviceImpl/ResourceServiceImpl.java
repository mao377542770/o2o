package com.ugfind.serviceImpl;


import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ugfind.dao.ResourceMapper;
import com.ugfind.dao.ResourceTypeMapper;
import com.ugfind.dao.UserResourceRlMapper;
import com.ugfind.model.PageInfoSearch;
import com.ugfind.model.Resource;
import com.ugfind.model.ResourcePoJo;
import com.ugfind.model.ResourceType;
import com.ugfind.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@javax.annotation.Resource
	private ResourceMapper resourceDao;
	@javax.annotation.Resource
	private ResourceTypeMapper resourceTypeDao;
	@javax.annotation.Resource
	private UserResourceRlMapper userResourceRlDao;
	
	@Override
	public int insertResource(Resource resource) {
		return resourceDao.insertSelective(resource);
	}

	@Override
	public List<ResourceType> getTypeList() {
		return  resourceTypeDao.getTypeList();
	}

	@Override
	public int updateResource(Resource resource) {	
		resource.setDate(new Date(System.currentTimeMillis()));
		resourceDao.updateByPrimaryKeySelective(resource);
		return userResourceRlDao.addTotal(resource.getUserId());
		
	}

	@Override
	public List<Resource> getResourceList(PageInfoSearch pageInfo) {
		return resourceDao.getResourceList(pageInfo);
	}

	@Override
	public void getCountByPage(PageInfoSearch pageInfo) {
	}

	@Override
	public List<Resource> getResourceOrderByDown() {
		return resourceDao.getResourceOrderByDown();
	}

	@Override
	public ResourcePoJo getResourceInfoById(Integer id) {
		return resourceDao.getResourceInfoById(id);
	}

	@Override
	public ResourceFilePoJo getDownFile(Integer id) {
		Resource downFile = resourceDao.getDownFile(id);
		
		
		ResourceFilePoJo resourceFilePoJo = new ResourceFilePoJo();
		resourceFilePoJo.setFile(new File(downFile.getPath()));
		resourceFilePoJo.setFileName(downFile.getFileName());
		resourceFilePoJo.setTitle(downFile.getTitle());
		return resourceFilePoJo;
	}

	//获得总资源数
	@Override
	public int getResourceTotal() {
		try {
			return resourceDao.getResourceTotal();
		} catch (Exception e) {
			return 0;
		}
	}
}
