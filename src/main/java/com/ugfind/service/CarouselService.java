package com.ugfind.service;

import java.util.List;

import com.ugfind.model.Carousel;

public interface CarouselService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carousel record);
    
    //获取本校的所有轮播图
    List<Carousel> getScholdCarousel(Integer schoolId);
    
    //手机端获取轮播图  默认只读取前三个
    List<Carousel> getScholdCarouselFront(Integer schoolId);
}