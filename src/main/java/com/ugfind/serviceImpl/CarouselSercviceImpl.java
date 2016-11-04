package com.ugfind.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugfind.dao.CarouselMapper;
import com.ugfind.model.Carousel;
import com.ugfind.service.CarouselService;

@Service
public class CarouselSercviceImpl implements CarouselService {

	@Autowired
	private CarouselMapper carouselDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return carouselDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Carousel record) {
		return carouselDao.insertSelective(record);
	}

	@Override
	public Carousel selectByPrimaryKey(Integer id) {
		return carouselDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Carousel record) {
		return carouselDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Carousel> getScholdCarousel(Integer schoolId) {
		return carouselDao.getScholdCarousel(schoolId);
	}

	@Override
	public List<Carousel> getScholdCarouselFront(Integer schoolId) {
		return carouselDao.getScholdCarouselFront(schoolId);
	}

}
