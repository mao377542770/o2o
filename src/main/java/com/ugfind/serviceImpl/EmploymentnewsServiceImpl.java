package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.EmploymentnewsMapper;
import com.ugfind.model.EmploymentNewsPage;
import com.ugfind.model.Employmentnews;
import com.ugfind.service.EmploymentnewsService;

@Service
public class EmploymentnewsServiceImpl implements EmploymentnewsService {

	@Autowired
	private EmploymentnewsMapper employmentDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return employmentDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Employmentnews record) {
		return employmentDao.insertSelective(record);
	}

	@Override
	public Employmentnews selectByPrimaryKey(Integer id) {
		return employmentDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Employmentnews record) {
		return employmentDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Employmentnews> getNewsBypage(EmploymentNewsPage newsPage) {
		return employmentDao.getNewsBypage(newsPage);
	}

	@Override
	public int updateNewsViewCount(Integer id) {
		return employmentDao.updateNewsViewCount(id);
	}

	@Override
	public int getSchoolNewsCount(EmploymentNewsPage newsPage) {
		return employmentDao.getSchoolNewsCount(newsPage);
	}


}
