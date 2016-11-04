package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.RecruitinfoMapper;
import com.ugfind.model.NewsPageConfig;
import com.ugfind.model.Recruitinfo;
import com.ugfind.service.RecruitinfoService;

@Service
public class RecruitinfoServiceImpl implements RecruitinfoService {

	@Autowired
	private RecruitinfoMapper recruitInfoDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return recruitInfoDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Recruitinfo record) {
		return recruitInfoDao.insertSelective(record);
	}

	@Override
	public Recruitinfo selectByPrimaryKey(Integer id) {
		return recruitInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Recruitinfo record) {
		return recruitInfoDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Recruitinfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRecruitInfoCount(NewsPageConfig newsPage) {
		return recruitInfoDao.getRecruitInfoCount(newsPage);
	}

	@Override
	public List<Recruitinfo> getRecruitInfoBypage(NewsPageConfig newsPage) {
		return recruitInfoDao.getRecruitInfoBypage(newsPage);
	}

	@Override
	public int updateRecruitInfoViewCount(Integer id) {
		return recruitInfoDao.updateRecruitInfoViewCount(id);
	}

}
