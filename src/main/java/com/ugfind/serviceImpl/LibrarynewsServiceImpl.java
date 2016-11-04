package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.LibrarynewsMapper;
import com.ugfind.model.Librarynews;
import com.ugfind.model.NewsPageConfig;
import com.ugfind.service.LibrarynewsService;

@Service
public class LibrarynewsServiceImpl implements LibrarynewsService {
	
	private LibrarynewsMapper libraryMapper;
	
	
	public LibrarynewsMapper getLibraryMapper() {
		return libraryMapper;
	}

	@Autowired
	public void setLibraryMapper(LibrarynewsMapper libraryMapper) {
		this.libraryMapper = libraryMapper;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return libraryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Librarynews record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Librarynews record) {
		return libraryMapper.insertSelective(record);
	}

	@Override
	public Librarynews selectByPrimaryKey(Integer id) {
		return libraryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Librarynews record) {
		return libraryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Librarynews record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Librarynews record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSchoolNewsCount(NewsPageConfig newsPage) {
		return libraryMapper.getSchoolNewsCount(newsPage);
	}

	@Override
	public List<Librarynews> getNewsBypage(NewsPageConfig newsPage) {
		return libraryMapper.getNewsBypage(newsPage);
	}

	@Override
	public Librarynews getNewsInfo(Integer newsId) {		
		return libraryMapper.selectByPrimaryKey(newsId);
	}

	@Override
	public int updateLibraryNewsViewCount(Integer id) {
		return libraryMapper.updateLibraryNewsViewCount(id);
	}

}
