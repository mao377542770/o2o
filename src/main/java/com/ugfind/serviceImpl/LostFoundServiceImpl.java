package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.LostFoundMapper;
import com.ugfind.model.LostFound;
import com.ugfind.model.LostFoundConfig;
import com.ugfind.model.LostFoundVo;
import com.ugfind.service.LostFoundService;

@Service
public class LostFoundServiceImpl implements LostFoundService {

	@Autowired
	private LostFoundMapper dao;

	@Override
	public int delete(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LostFound record) {
		return dao.insertSelective(record);
	}

	@Override
	public int update(LostFound record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<LostFoundVo> getLostFoundList(LostFoundConfig pageConfig) {
		return dao.getLostFoundList(pageConfig);
	}

	@Override
	public int getLostFoundCount(LostFoundConfig pageConfig) {
		return dao.getLostFoundCount(pageConfig);
	}

	@Override
	public int insertSelective(LostFound record) {
		return dao.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(LostFound record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<LostFoundVo> getLostFoundListRemark(LostFoundConfig pageConfig) {
		return dao.getLostFoundListRemark(pageConfig);
	}
	
	

}
