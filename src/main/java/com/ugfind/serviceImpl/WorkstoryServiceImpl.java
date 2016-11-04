package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.WorkstoryMapper;
import com.ugfind.model.NewsPageConfig;
import com.ugfind.model.Workstory;
import com.ugfind.service.WorkstoryService;

@Service
public class WorkstoryServiceImpl implements WorkstoryService {

	@Autowired
	private WorkstoryMapper workStoryDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return workStoryDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Workstory record) {
		return workStoryDao.insertSelective(record);
	}

	@Override
	public Workstory selectByPrimaryKey(Integer id) {
		return workStoryDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Workstory record) {
		return workStoryDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int getWorkstoryCount(NewsPageConfig newsPage) {
		return workStoryDao.getWorkstoryCount(newsPage);
	}

	@Override
	public List<Workstory> getWorkstoryBypage(NewsPageConfig newsPage) {
		return workStoryDao.getWorkstoryBypage(newsPage);
	}

	@Override
	public int updateWorkstoryViewCount(Integer id) {
		return workStoryDao.updateWorkstoryViewCount(id);
	}

}
