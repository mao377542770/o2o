package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.BespokeMapper;
import com.ugfind.model.Bespoke;
import com.ugfind.model.BespokeConfig;
import com.ugfind.model.BespokePageConfig;
import com.ugfind.model.BespokeParameter;
import com.ugfind.model.BoardroomVo;
import com.ugfind.service.BespokeService;

@Service
public class BespokeServiceImpl implements BespokeService {

	private BespokeMapper bespokeDao;
	
	
	public BespokeMapper getBespokeDao() {
		return bespokeDao;
	}
	@Autowired
	public void setBespokeDao(BespokeMapper bespokeDao) {
		this.bespokeDao = bespokeDao;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return bespokeDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Bespoke record) {
		return bespokeDao.insertSelective(record);
	}

	@Override
	public Bespoke selectByPrimaryKey(Integer id) {
		return bespokeDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Bespoke record) {
		return bespokeDao.updateByPrimaryKeySelective(record);
	}
	@Override
	public List<Bespoke> getBespokeByRoom(BespokeParameter para) {
		return bespokeDao.getBespokeByRoom(para);
	}
	@Override
	public int checkBespokeState(BespokeParameter para) {
		return bespokeDao.checkBespokeState(para);
	}
	@Override
	public List<BoardroomVo> getFrontRoomList(BespokeParameter para) {
		return bespokeDao.getFrontRoomList(para);
	}
	@Override
	public List<Bespoke> getBespokeBack(BespokeConfig config) {
		return bespokeDao.getBespokeBack(config);
	}
	@Override
	public int judgeTime(BespokeParameter para) {
		return bespokeDao.judgeTime(para);
	}
	@Override
	public List<Bespoke> getBespokeBackByRoomIdAndDate(Integer roomId,
			String startTime, String endTime) {
		return bespokeDao.getBespokeBackByRoomIdAndDate(roomId, startTime, endTime);
	}
	@Override
	public int getDeleteCount(Integer schoolId) {
		return bespokeDao.getDeleteCount(schoolId);
	}
	@Override
	public List<Bespoke> selectDeleteRecord(BespokePageConfig pageConfig) {
		return bespokeDao.selectDeleteRecord(pageConfig);
	}


}
