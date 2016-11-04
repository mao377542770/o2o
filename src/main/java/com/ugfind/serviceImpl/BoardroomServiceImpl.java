package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.BoardroomMapper;
import com.ugfind.model.Boardroom;
import com.ugfind.service.BoardroomService;

@Service
public class BoardroomServiceImpl implements BoardroomService {

	private BoardroomMapper boardroomDao;
	
	
	public BoardroomMapper getBoardroomDao() {
		return boardroomDao;
	}

	@Autowired
	public void setBoardroomDao(BoardroomMapper boardroomDao) {
		this.boardroomDao = boardroomDao;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return boardroomDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Boardroom record) {
		return boardroomDao.insertSelective(record);
	}

	@Override
	public Boardroom selectByPrimaryKey(Integer id) {
		return boardroomDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Boardroom record) {
		return boardroomDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Boardroom> getAllBoardroom(Integer schoolId) {
		return boardroomDao.getAllBoardroom(schoolId);
	}

}
