package com.ugfind.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.EmploymentNewsPage;
import com.ugfind.model.Employmentnews;
import com.ugfind.model.NewsPageConfig;
import com.ugfind.model.Recruitinfo;
import com.ugfind.model.Result;
import com.ugfind.model.Workstory;
import com.ugfind.service.EmploymentnewsService;
import com.ugfind.service.RecruitinfoService;
import com.ugfind.service.WorkstoryService;

@Controller
@RequestMapping("/employment")
public class EmploymentController {
	
	@Autowired
	private EmploymentnewsService employmentService;
	@Autowired
	private RecruitinfoService recruitInfoService;
	@Autowired
	private WorkstoryService workStoryService;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 分页获取公告列表
	 * @param newsPage
	 * @return
	 */
	@RequestMapping("/getNewsList")
	@ResponseBody
	public Result getNewsList(EmploymentNewsPage newsPage){
		Result result = new Result();
		//获取总条数
		Integer totalCount = employmentService.getSchoolNewsCount(newsPage);
		result.setMsg(totalCount+"");
		//公告
		newsPage.setCurrentIndex(newsPage.getCurrentPage()*newsPage.getItemsPerPage());
		List<Employmentnews> newsList = employmentService.getNewsBypage(newsPage);
		if(newsList!=null){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		result.setData(newsList);
		return result;
	}
	
	
	/**
	 * 获得新闻详情
	 * @return
	 */
	@RequestMapping("/getNewsInfo")
	@ResponseBody
	public Result getNewsInfo(Integer newsId){
		Result result = new Result();
		Employmentnews librarynews =employmentService.selectByPrimaryKey(newsId);
		result.setResult(1,librarynews, null);
		return result;
	}
	
	/**
	 * 添加公告
	 * @param news
	 * @return
	 */
	@RequestMapping("/addNews")
	@ResponseBody
	public Result addNews(Employmentnews news){
		Result result = new Result();
		try {
			employmentService.insertSelective(news);
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(2);
		}
		return result;
	}
	/**
	 * 修改一条公告
	 * @param news
	 * @return
	 */
	@RequestMapping("/updateNews")
	@ResponseBody
	public Result updateNews(Employmentnews news){
		Result result = new Result();
		try {
			employmentService.updateByPrimaryKeySelective(news);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 删除一条公告
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteNews")
	@ResponseBody
	public Result deleteNews(HttpServletRequest request){
		Result result = new Result();
		Integer id  = Integer.valueOf(request.getParameter("newsId"));
		try {
			employmentService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	
	/**
	 * 公告阅读次数加1
	 * @return
	 */
	@RequestMapping("/libraryNewsView")
	@ResponseBody
	public Result libraryNewsView(Integer newsId){
		Result result = new Result();
		employmentService.updateNewsViewCount(newsId);
		result.setStatus(1);
		return result;
	}
	
	
	/**
	 * 分页获招聘资讯列表
	 * @param newsPage
	 * @return
	 */
	@RequestMapping("/getRecruitList")
	@ResponseBody
	public Result getRecruitList(NewsPageConfig newsPage){
		Result result = new Result();
		//获取总条数
		Integer totalCount = recruitInfoService.getRecruitInfoCount(newsPage);
		result.setMsg(totalCount+"");
		//公告
		newsPage.setCurrentIndex(newsPage.getCurrentPage()*newsPage.getItemsPerPage());
		List<Recruitinfo> newsList = recruitInfoService.getRecruitInfoBypage(newsPage);
		if(newsList!=null){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		result.setData(newsList);
		return result;
	}
	
	
	/**
	 * 获得资讯详情
	 * @return
	 */
	@RequestMapping("/getRecruitInfo")
	@ResponseBody
	public Result getRecruitInfo(Integer newsId){
		Result result = new Result();
		Recruitinfo recruitInfo =recruitInfoService.selectByPrimaryKey(newsId);
		result.setResult(1,recruitInfo, null);
		return result;
	}
	
	/**
	 * 发布招聘资讯
	 * @param recruitInfo
	 * @return
	 */
	@RequestMapping("/addRecruitinfo")
	@ResponseBody
	public Result addRecruitinfo(Recruitinfo recruitInfo){
		Result result = new Result();
		recruitInfo.setPublishDate(sdf.format(new Date()));
		try {
			recruitInfoService.insertSelective(recruitInfo);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	/**
	 * 修改招聘资讯
	 * @param recruitInfo
	 * @return
	 */
	@RequestMapping("/updateRecruitinfo")
	@ResponseBody
	public Result updateRecruitinfo(Recruitinfo recruitInfo){
		Result result = new Result();
		try {
			recruitInfoService.updateByPrimaryKeySelective(recruitInfo);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 删除一条招聘资讯
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRecruitinfo")
	@ResponseBody
	public Result deleteRecruitinfo(Integer id){
		Result result = new Result();
		try {
			recruitInfoService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	
	/**
	 * 招聘资讯阅读次数加1
	 * @return
	 */
	@RequestMapping("/RecruitView")
	@ResponseBody
	public Result RecruitView(Integer id){
		Result result = new Result();
		try {
			recruitInfoService.updateRecruitInfoViewCount(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 分页获职场故事列表
	 * @param newsPage
	 * @return
	 */
	@RequestMapping("/getworkStoryList")
	@ResponseBody
	public Result getworkStoryList(NewsPageConfig newsPage){
		Result result = new Result();
		//获取总条数
		Integer totalCount = workStoryService.getWorkstoryCount(newsPage);
		result.setMsg(totalCount+"");
		//公告
		newsPage.setCurrentIndex(newsPage.getCurrentPage()*newsPage.getItemsPerPage());
		List<Workstory> newsList = workStoryService.getWorkstoryBypage(newsPage);
		if(newsList!=null){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		result.setData(newsList);
		return result;
	}
	
	
	/**
	 * 获得职场故事详情
	 * @return
	 */
	@RequestMapping("/getWorkStory")
	@ResponseBody
	public Result getWorkStory(Integer newsId){
		Result result = new Result();
		Workstory recruitInfo =workStoryService.selectByPrimaryKey(newsId);
		result.setResult(1,recruitInfo, null);
		return result;
	}
	
	/**
	 * 发布职场故事
	 * @param workStory
	 * @return
	 */
	@RequestMapping("/addWorkStory")
	@ResponseBody
	public Result addWrokStory(Workstory workStory){
		Result result = new Result();
		workStory.setPublishDate(sdf.format(new Date()));
		try {
			workStoryService.insertSelective(workStory);
			result.setStatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(2);
		}
		return result;
	}
	/**
	 * 修改职场故事
	 * @param workStory
	 * @return
	 */
	@RequestMapping("/updateWorkstory")
	@ResponseBody
	public Result updateWorkstory(Workstory workStory){
		Result result = new Result();
		try {
			workStoryService.updateByPrimaryKeySelective(workStory);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	/**
	 * 删除一条职场故事
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteWorkstory")
	@ResponseBody
	public Result deleteWorkstory(Integer id){
		Result result = new Result();
		try {
			workStoryService.deleteByPrimaryKey(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	
	/**
	 * 职场故事阅读次数加1
	 * @return
	 */
	@RequestMapping("/workStoryView")
	@ResponseBody
	public Result workStoryView(Integer id){
		Result result = new Result();
		try {
			workStoryService.updateWorkstoryViewCount(id);
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
}
