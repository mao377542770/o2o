package com.ugfind.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.DeadlineMapper;
import com.ugfind.model.Deadline;
import com.ugfind.service.DeadlineService;

@Service
public class DeadlineServiceImpl implements DeadlineService {
	private DeadlineMapper deadLineMapper;
	
	public DeadlineMapper getDeadLineMapper() {
		return deadLineMapper;
	}
	@Autowired
	public void setDeadLineMapper(DeadlineMapper deadLineMapper) {
		this.deadLineMapper = deadLineMapper;
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Deadline record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Deadline record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Deadline selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Deadline record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Deadline record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Deadline> getMyAllModule(Map<String, Integer> map) {
		return deadLineMapper.getMyAllModule(map);
	}
	@Override
	public int updateSequence(List<Deadline> list) {
		return deadLineMapper.updateSequence(list);
	}

}
