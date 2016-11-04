package com.ugfind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.Carousel;
import com.ugfind.model.Result;
import com.ugfind.service.CarouselService;

@Controller
@RequestMapping("/carousel")
public class CarouselController {
	
	@Autowired
	private CarouselService carouselService;
	
	/**
	 * 获取本校的轮播图列表 
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/getCarouselList")
	@ResponseBody
	public Result getCarouselList(Integer schoolId){
		Result result = new Result();
		List<Carousel> carouselList = carouselService.getScholdCarousel(schoolId);
		if(carouselList !=null){
			result.setStatus(1);
		}
		result.setData(carouselList);
		return result;
	}
	
	/**
	 * 手机端获取本校的轮播图列表 
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/getCarouselListFront")
	@ResponseBody
	public Result getCarouselListFront(Integer schoolId){
		Result result = new Result();
		List<Carousel> carouselList = carouselService.getScholdCarouselFront(schoolId);
		if(carouselList !=null){
			result.setStatus(1);
		}
		result.setData(carouselList);
		return result;
	}
	
	/**
	 * 添加轮播图
	 * @param carousel
	 * @return
	 */
	@RequestMapping("/addCarousel")
	@ResponseBody
	public Result addCarousel(Carousel carousel){
		Result result = new Result();
		carouselService.insertSelective(carousel);
		result.setStatus(1);
		return result;
	}
	/**
	 * 修改
	 * @param carousel
	 * @return
	 */
	@RequestMapping("/updateCarousel")
	@ResponseBody
	public Result updateCarousel(Carousel carousel){
		Result result = new Result();
		try {
			carouselService.updateByPrimaryKeySelective(carousel);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 删除轮播图
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCarousel")
	@ResponseBody
	public Result deleteCarousel(Integer id){
		Result result = new Result();
		carouselService.deleteByPrimaryKey(id);
		result.setStatus(1);
		return result;
	}
}
