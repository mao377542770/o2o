package com.ugfind.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.Result;
import com.ugfind.model.Schoolvisits;
import com.ugfind.model.User;
import com.ugfind.service.SchoolService;
import com.ugfind.service.UserService;
import com.ugfind.util.MD5Util;

@Controller
public class RegistController {
	
	@Resource
	private Result result;
	@Resource
	private UserService userService;
	@Resource
	private SchoolService schoolService;
	
	@RequestMapping("/checkUserTelphone")
	@ResponseBody
	public Result checkUserTelphone(String telphone){
		if(userService.checkUserTelphone(telphone)){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		return result;
	}
	
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Result checkUserName(String userName){
		if(userService.checkUserName(userName)){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		return result;
	}
	
	@RequestMapping("/checkNickName")
	@ResponseBody
	public Result checkNickName(String nickName){
		if(userService.checkNickName(nickName)){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		return result;
	}
	
	@RequestMapping("/getSchoolList")
	@ResponseBody
	public Result getSchoolList(String keyWord,String city){
		result.setResult(1, schoolService.getSchoolList(keyWord, city), null);
		return result;
	}
	
	@RequestMapping("/getSchoolVisitiInfo")
	@ResponseBody
	public Result getSchoolVisitiInfo(Integer schoolId){
		Schoolvisits schoolInfo = schoolService.selectSchoolVisitsBySchoolId(schoolId);
		result.setResult(1, schoolInfo, null);
		return result;
	}
	
	@RequestMapping("/registUser")
	@ResponseBody
	public Result registUser(User user){
		user.setValid(1);
		user.setUserType(2);  //2.代表学生
		user.setPassWord(MD5Util.string2MD5("key" +user.getPassWord()));
		user.setCreateDate(new Date(System.currentTimeMillis()));
		user.setEmail(user.getUserName());
		if(userService.insertUser(user)>0){
			//注册成功,该学校人数+1
			schoolService.addSchoolVisits(user.getSchoolId());
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		return result;
	}
}
