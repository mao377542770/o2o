package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.ActivityMapper;
import com.ugfind.dao.ActivityvoteMapper;
import com.ugfind.model.Activity;
import com.ugfind.model.ActivityConfig;
import com.ugfind.model.ActivityVo;
import com.ugfind.model.Activityvote;
import com.ugfind.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityMapper activityDao;
	@Autowired
	private ActivityvoteMapper voteDao;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return activityDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Activity record) {
		return activityDao.insertSelective(record);
	}

	@Override
	public Activity selectByPrimaryKey(Integer id) {
		return activityDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Activity record) {
		return activityDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Activity> getAllActivity(ActivityConfig acConfig) {
		return activityDao.getAllActivity(acConfig);
	}

	@Override
	public int getAcCount(ActivityConfig acConfig) {
		return activityDao.getAcCount(acConfig);
	}

	@Override
	public int addViewCount(Integer id) {
		return activityDao.addViewCount(id);
	}

	@Override
	public List<ActivityVo> getFrontActivity(ActivityConfig acConfig) {
		return activityDao.getFrontActivity(acConfig);
	}

	@Override
	public int getFrontActivityCount(ActivityConfig acConfig) {
		return activityDao.getFrontActivityCount(acConfig);
	}

	@Override
	public ActivityVo getFrontActivityInfoById(Integer id, Integer userId) {
		return activityDao.getFrontActivityInfoById(id, userId);
	}

	@Override
	public int addActivityVote(Activityvote actvote) {
		return voteDao.insertSelective(actvote);
	}

}
