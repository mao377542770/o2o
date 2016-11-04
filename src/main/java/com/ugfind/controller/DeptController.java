package com.ugfind.controller;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.Deadline;
import com.ugfind.model.Result;
import com.ugfind.model.Subscribe;
import com.ugfind.model.User;
import com.ugfind.service.DeadlineService;
import com.ugfind.service.DeptService;
import com.ugfind.util.LoginUtil;

@Controller
public class DeptController {
	
	@Resource
	private Result result;
	@Resource
	private DeptService deptService;
	@Resource
	private DeadlineService deadLineService;
	
	//根据学校id  获取开通的部门列表
	@RequestMapping("/getDeptBySchoolId")
	@ResponseBody
	public Result getDeptBySchoolId(Integer schoolId){
		result.setData(deptService.getDeptBySchoolId(schoolId));
		result.setStatus(1);
		return result;
	}
	
		/**
		 * 获取部门已经开通的功能
		 * @param request
		 * @return
		 */
		@RequestMapping("/getMyModule")
		@ResponseBody
		public Result getDepartModule(HttpServletRequest request){
			Result result = new Result();
			Integer schoolId = null;
			Integer deptId = null;
			if(!request.getParameter("schoolId").equals("") && request.getParameter("schoolId") != null){
				schoolId = Integer.valueOf(request.getParameter("schoolId"));
			}
			if(!request.getParameter("deptId").equals("") && request.getParameter("deptId") != null){
				deptId = Integer.valueOf(request.getParameter("deptId"));
			}
			if(schoolId != null && deptId != null){
				Map<String,Integer> map = new HashMap<String, Integer>();
				map.put("schoolId", schoolId);
				map.put("deptId", deptId);
				List<Deadline> deadLineList = deadLineService.getMyAllModule(map);
				result.setData(deadLineList);
			}else{
				result.setData(null);
			}
			result.setStatus(1);
			return result;
		}
		
		/**
		 * 提交开通
		 * @return
		 */
		@RequestMapping("/front/saveSubscribe")
		@ResponseBody
		public Result saveSubscribe(HttpServletRequest request,Subscribe subscribe){
			Result result = new Result();
			User user = LoginUtil.getUserInfo(request);
			if(user == null || user.getId() == null){
				result.setResult(2, null, "请确认是否登录");
				return result;
			}
			subscribe.setUserId(user.getId());
			subscribe.setApplyDate(new Date(System.currentTimeMillis()));
			if(deptService.saveSubscribe(subscribe)){
				result.setResult(1, null, null);
			}else{
				result.setResult(2, null, "提交失败,您已提交过了");
			}
			return result;
		}
	}
