package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.VoteoptionMapper;
import com.ugfind.model.Voteoption;
import com.ugfind.service.VoteoptionService;

@Service
public class VoteoptionServiceImpl implements VoteoptionService {

	private VoteoptionMapper voteOptionDao;
	
	
	public VoteoptionMapper getVoteOptionDao() {
		return voteOptionDao;
	}

	@Autowired
	public void setVoteOptionDao(VoteoptionMapper voteOptionDao) {
		this.voteOptionDao = voteOptionDao;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return voteOptionDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Voteoption record) {
		return voteOptionDao.insertSelective(record);
	}

	@Override
	public Voteoption selectByPrimaryKey(Integer id) {
		return voteOptionDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Voteoption record) {
		return voteOptionDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Voteoption> selectOptionsByActivityId(Integer activityId) {
		return voteOptionDao.selectOptionsByActivityId(activityId);
	}

}
