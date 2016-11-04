package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.LiteraturetypeMapper;
import com.ugfind.model.Literaturetype;
import com.ugfind.service.LiteratureTypeService;

@Service
public class LiteratureTypeServiceImpl implements LiteratureTypeService {

	private LiteraturetypeMapper literatureTypeMapper;
	
	
	public LiteraturetypeMapper getLiteratureTypeMapper() {
		return literatureTypeMapper;
	}
	@Autowired
	public void setLiteratureTypeMapper(LiteraturetypeMapper literatureTypeMapper) {
		this.literatureTypeMapper = literatureTypeMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return literatureTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Literaturetype record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Literaturetype record) {
		return literatureTypeMapper.insertSelective(record);
	}

	@Override
	public Literaturetype selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Literaturetype record) {
		return literatureTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Literaturetype record) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Literaturetype> getAllType(Integer schoolId) {
		return literatureTypeMapper.getAllType(schoolId);
	}

}
