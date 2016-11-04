package com.ugfind.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.ActivityConfig;
import com.ugfind.model.ActivityVo;
import com.ugfind.model.Activityvote;
import com.ugfind.model.Result;
import com.ugfind.service.ActivityService;
import com.ugfind.service.ActivityvoteService;
import com.ugfind.service.VoteoptionService;

/**
 * 前台获取活动
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/front")
public class FrontActivityController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActivityvoteService activityVoteService;
	@Autowired
	private VoteoptionService voteOptionService;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	/**
	 * 分页获取活动投票列表
	 * @param acConfig
	 * @return
	 */
	@RequestMapping("/getActivityList")
	@ResponseBody
	public Result getActivityList(ActivityConfig acConfig){
		Result result = new Result();
		//获取总条数
		Integer totalCount = activityService.getFrontActivityCount(acConfig);
		result.setMsg(totalCount+"");
		//公告
		acConfig.setCurrentIndex((acConfig.getCurrentPage() - 1)*acConfig.getItemsPerPage());
		List<ActivityVo> list = activityService.getFrontActivity(acConfig);
		if(list!=null){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		result.setData(list);
		return result;
	}
	
	/**
	 * 通过id获取活动投票详情
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/getActivityInfo")
	@ResponseBody
	public Result getActivityInfo(Integer activityId, Integer userId){
		Result result = new Result();
		ActivityVo info = activityService.getFrontActivityInfoById(activityId, userId);
		result.setResult(1, info, null);
		return result;
	}
	
	/**
	 * 增加活动投票浏览次数
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/addActivityViewCount")
	@ResponseBody
	public Result addActivityViewCount(Integer activityId){
		Result result = new Result();
		activityService.addViewCount(activityId);
		result.setResult(1, null, null);
		return result;
	}
	
	/**
	 * 活动投票
	 * @param activityId
	 * @return
	 */
	@RequestMapping("/vote")
	@ResponseBody
	public Result vote(Activityvote actvote){
		Result result = new Result();
		actvote.setId(null);
		actvote.setVoteDate(sdf.format(new Date()));
		int n = activityService.addActivityVote(actvote);
		if(n > 0){
			result.setResult(1, null, null);
		}else{
			result.setResult(2, null, null);
		}
		return result;
	}
	
	
}
