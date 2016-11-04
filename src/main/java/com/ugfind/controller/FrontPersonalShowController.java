package com.ugfind.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.AffectMoment;
import com.ugfind.model.PageVo;
import com.ugfind.model.RelaxMoment;
import com.ugfind.model.Result;
import com.ugfind.service.AffectMomentService;
import com.ugfind.service.RelaxMomentService;

/**
 * 前台获取个人秀的感动瞬间和轻松时刻
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/front")
public class FrontPersonalShowController {
	@Autowired
	private AffectMomentService affectService;
	@Autowired
	private RelaxMomentService relaxService;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	

	/**
	 * 分页获取感动瞬间
	 * @param pageVo
	 * @return
	 */
	@RequestMapping("/getAffectList")
	@ResponseBody
	public Result getAffectList(PageVo pageVo){
		Result result = new Result();
		//获取总条数
		Integer totalCount = affectService.getAffectMomentCount();
		result.setMsg(totalCount+"");
		pageVo.setCurrentIndex(pageVo.getCurrentPage()*pageVo.getItemsPerPage());
		List<AffectMoment> list = affectService.getAffectMomentByPage(pageVo);
		if(list!=null){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		result.setData(list);
		return result;
	}
	
	/**
	 * 通过id获取感动瞬间详情
	 * @param affectId
	 * @return
	 */
	@RequestMapping("/getAffectInfo")
	@ResponseBody
	public Result getActivityInfo(Integer affectId){
		Result result = new Result();
		AffectMoment info = affectService.getAffectMomentById(affectId);
		result.setResult(1, info, null);
		return result;
	}
	
	/**
	 * 通过id,感动瞬间 阅读次数加1
	 * @param affectId
	 * @return
	 */
	@RequestMapping("/addAffectView")
	@ResponseBody
	public Result addAffectView(Integer affectId){
		Result result = new Result();
		affectService.addViewCount(affectId);
		result.setResult(1, null, null);
		return result;
	}
	
	/**
	 * 分页获取轻松时刻
	 * @param pageVo
	 * @return
	 */
	@RequestMapping("/getRelaxList")
	@ResponseBody
	public Result getRelaxList(PageVo pageVo){
		Result result = new Result();
		//获取总条数
		Integer totalCount = relaxService.getRelaxMomentCount();
		result.setMsg(totalCount+"");
		pageVo.setCurrentIndex(pageVo.getCurrentPage()*pageVo.getItemsPerPage());
		List<RelaxMoment> list = relaxService.getRelaxMomentByPage(pageVo);
		if(list!=null){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		result.setData(list);
		return result;
	}
	
	/**
	 * 通过id获取轻松时刻详情
	 * @param realxId
	 * @return
	 */
	@RequestMapping("/getRelaxInfo")
	@ResponseBody
	public Result getRelaxInfo(Integer relaxId){
		Result result = new Result();
		RelaxMoment info = relaxService.getRelaxMomentById(relaxId);
		result.setResult(1, info, null);
		return result;
	}
	
	/**
	 * 通过id,轻松时刻 阅读次数加1
	 * @param realxId
	 * @return
	 */
	@RequestMapping("/addRelaxView")
	@ResponseBody
	public Result addRelaxView(Integer relaxId){
		Result result = new Result();
		relaxService.addViewCount(relaxId);
		result.setResult(1, null, null);
		return result;
	}
	
}
