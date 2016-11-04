package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.ModuleMapper;
import com.ugfind.model.Module;
import com.ugfind.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	private ModuleMapper moduleMapper;
	
	public ModuleMapper getModuleMapper() {
		return moduleMapper;
	}
	@Autowired
	public void setModuleMapper(ModuleMapper moduleMapper) {
		this.moduleMapper = moduleMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Module record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Module record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Module selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Module record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Module record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Module> getAllModule(Integer deptId) {
		return moduleMapper.getAllModule(deptId);
	}

}
