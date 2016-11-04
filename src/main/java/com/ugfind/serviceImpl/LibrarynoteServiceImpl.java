package com.ugfind.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.LibrarynoteMapper;
import com.ugfind.model.Librarynote;
import com.ugfind.service.LibrarynoteService;

@Service
public class LibrarynoteServiceImpl implements LibrarynoteService {
	private LibrarynoteMapper libraryNoteDao;
	
	public LibrarynoteMapper getLibraryNoteDao() {
		return libraryNoteDao;
	}

	@Autowired
	public void setLibraryNoteDao(LibrarynoteMapper libraryNoteDao) {
		this.libraryNoteDao = libraryNoteDao;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return libraryNoteDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Librarynote record) {
		return libraryNoteDao.insertSelective(record);
	}

	@Override
	public Librarynote selectByPrimaryKey(Integer id) {
		return libraryNoteDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Librarynote record) {
		return libraryNoteDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public Librarynote getNoteBydeptAndSchool(Map<String, Integer> map) {
		return libraryNoteDao.getNoteBydeptAndSchool(map);
	}

}
