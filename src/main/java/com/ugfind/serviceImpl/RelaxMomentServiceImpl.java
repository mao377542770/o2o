package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.RelaxMomentMapper;
import com.ugfind.model.PageVo;
import com.ugfind.model.RelaxMoment;
import com.ugfind.service.RelaxMomentService;

@Service
public class RelaxMomentServiceImpl implements RelaxMomentService {
	@Autowired
	private RelaxMomentMapper dao;

	@Override
	public List<RelaxMoment> getRelaxMomentByPage(PageVo pageVo) {
		return dao.getRelaxMomentByPage(pageVo);
	}

	@Override
	public int getRelaxMomentCount() {
		return dao.getRelaxMomentCount();
	}

	@Override
	public RelaxMoment getRelaxMomentById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int addViewCount(Integer id) {
		return dao.addViewCount(id);
	}

}
