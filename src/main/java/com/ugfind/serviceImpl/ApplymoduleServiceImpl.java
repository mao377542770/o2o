package com.ugfind.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.ApplymoduleMapper;
import com.ugfind.model.Applymodule;
import com.ugfind.service.ApplymoduleService;

@Service
public class ApplymoduleServiceImpl implements ApplymoduleService {

	private ApplymoduleMapper applyModuleMapper;

	public ApplymoduleMapper getApplyModuleMapper() {
		return applyModuleMapper;
	}
	
	@Autowired
	public void setApplyModuleMapper(ApplymoduleMapper applyModuleMapper) {
		this.applyModuleMapper = applyModuleMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Applymodule record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Applymodule record) {
		return applyModuleMapper.insertSelective(record);
	}

	@Override
	public Applymodule selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Applymodule record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Applymodule record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
