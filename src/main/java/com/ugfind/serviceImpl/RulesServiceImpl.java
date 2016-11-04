package com.ugfind.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.RulesMapper;
import com.ugfind.model.Rules;
import com.ugfind.service.RulesService;

@Service
public class RulesServiceImpl implements RulesService {
	private RulesMapper rulesMapper;
	
	public RulesMapper getRulesMapper() {
		return rulesMapper;
	}

	@Autowired
	public void setRulesMapper(RulesMapper rulesMapper) {
		this.rulesMapper = rulesMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return rulesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Rules record) {
		return rulesMapper.insertSelective(record);
	}

	@Override
	public Rules selectByPrimaryKey(Integer id) {
		return rulesMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Rules record) {
		return rulesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Rules> getRules(Map<String, Integer> map) {
		return rulesMapper.getRules(map);
	}

}
