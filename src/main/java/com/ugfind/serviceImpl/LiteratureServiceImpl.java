package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.LiteratureMapper;
import com.ugfind.model.Literature;
import com.ugfind.model.LiteratureConfig;
import com.ugfind.service.LiteratureService;

@Service
public class LiteratureServiceImpl implements LiteratureService {

	private LiteratureMapper literatureMapper;
	
	public LiteratureMapper getLiteratureMapper() {
		return literatureMapper;
	}
	
	@Autowired
	public void setLiteratureMapper(LiteratureMapper literatureMapper) {
		this.literatureMapper = literatureMapper;
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return literatureMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Literature record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Literature record) {
		return literatureMapper.insertSelective(record);
	}

	@Override
	public Literature selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Literature record) {
		return literatureMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Literature record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Literature record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Literature> getAllLiterature(LiteratureConfig liteCon) {
		return literatureMapper.getAllLiterature(liteCon);
	}

	@Override
	public int getLiteratureCount(LiteratureConfig liteCon) {
		return literatureMapper.getLiteratureCount(liteCon);
	}

}
