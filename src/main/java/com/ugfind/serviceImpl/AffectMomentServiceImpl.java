package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.AffectMomentMapper;
import com.ugfind.model.AffectMoment;
import com.ugfind.model.PageVo;
import com.ugfind.service.AffectMomentService;

@Service
public class AffectMomentServiceImpl implements AffectMomentService {
	@Autowired
	private AffectMomentMapper dao;

	@Override
	public List<AffectMoment> getAffectMomentByPage(PageVo pageVo) {
		return dao.getAffectMomentByPage(pageVo);
	}

	@Override
	public int getAffectMomentCount() {
		return dao.getAffectMomentCount();
	}

	@Override
	public AffectMoment getAffectMomentById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int addViewCount(Integer id) {
		return dao.addViewCount(id);
	}

}
