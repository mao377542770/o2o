package com.ugfind.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.ActivityvoteMapper;
import com.ugfind.model.Activityvote;
import com.ugfind.service.ActivityvoteService;

@Service
public class ActivityvoteServiceImpl implements ActivityvoteService {
	
	private ActivityvoteMapper activityVoteDao;
	
	public ActivityvoteMapper getActivityVoteDao() {
		return activityVoteDao;
	}
	@Autowired
	public void setActivityVoteDao(ActivityvoteMapper activityVoteDao) {
		this.activityVoteDao = activityVoteDao;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return activityVoteDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Activityvote record) {
		return activityVoteDao.insertSelective(record);
	}

	@Override
	public Activityvote selectByPrimaryKey(Integer id) {
		return activityVoteDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Activityvote record) {
		return activityVoteDao.updateByPrimaryKeySelective(record);
	}

}
