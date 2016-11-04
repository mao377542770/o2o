package com.ugfind.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ugfind.dao.SchooldeptMapper;
import com.ugfind.dao.SubscribeMapper;
import com.ugfind.model.Schooldept;
import com.ugfind.model.Subscribe;
import com.ugfind.service.DeptService;

@Repository
public class DeptServiceImpl implements DeptService {
	
	@Resource
	private SchooldeptMapper schoolDeptDao;
	@Resource
	private SubscribeMapper subscribeDao;

	@Override
	public List<Schooldept>  getDeptBySchoolId(Integer schoolId){
		return schoolDeptDao.getDeptBySchoolId(schoolId);
	}

	@Override
	public boolean saveSubscribe(Subscribe subscribe) {
		try {
			if(subscribeDao.insert(subscribe) > 0){
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	
}
