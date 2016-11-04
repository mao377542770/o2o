package com.ugfind.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.LostFound;
import com.ugfind.model.LostFoundConfig;
import com.ugfind.model.LostFoundVo;
import com.ugfind.model.Result;
import com.ugfind.service.LostFoundService;

/**
 * 失物招领
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/lostFound")
public class LostFoundController {
	@Autowired
	private LostFoundService lostFoundService;
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//后台方法
	/**
	 * 后台管理员发布失物招领
	 * @param lostFound
	 * @return
	 */
	@RequestMapping("/addLostFound")
	@ResponseBody
	public Result addLostFound(LostFound lostFound){
		Result result = new Result();
		lostFound.setPublishDate(sdf.format(new Date()));
		lostFoundService.insertSelective(lostFound);
		result.setStatus(1);
		return result;
	}
	
	/**
	 * 修改失物招领信息
	 * @param lostFound
	 * @return
	 */
	@RequestMapping("/updateLostFound")
	@ResponseBody
	public Result updateLostFound(LostFound lostFound){
		Result result = new Result();
		try {
			lostFoundService.updateByPrimaryKeySelective(lostFound);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param lostFound
	 * @return
	 */
	@RequestMapping("/deleteLostFound")
	@ResponseBody
	public Result deleteLostFound(LostFound lostFound){
		Result result = new Result();
		try {
			lostFoundService.delete(lostFound.getId());
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	
	//以下是前台获取的方法
	/**
	 * 获取失物招领列表
	 * @param pageConfig
	 * @return
	 */
	@RequestMapping("/getFrontLostFoundList")
	@ResponseBody
	public Result getFrontLostFoundList(LostFoundConfig pageConfig){
		Result result = new Result();
		//获取总条数
		Integer totalCount = lostFoundService.getLostFoundCount(pageConfig);
		result.setMsg(totalCount+"");
		//list
		pageConfig.setCurrentIndex((pageConfig.getCurrentPage() - 1)*pageConfig.getItemsPerPage());
		List<LostFoundVo> list = null;
		if(pageConfig.getState() != null &&pageConfig.getState()==1){
			list = lostFoundService.getLostFoundListRemark(pageConfig);
		}else {
			list = lostFoundService.getLostFoundList(pageConfig);
		}
		if(list!=null){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		result.setData(list);
		return result;
	}
	
}
