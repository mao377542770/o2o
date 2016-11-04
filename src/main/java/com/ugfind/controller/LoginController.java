package com.ugfind.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;


import com.ugfind.model.Result;

import com.ugfind.service.LoginService;
import com.ugfind.util.LoginUtil;

@Controller
public class LoginController {
	
	@Resource
	private LoginService loginService;
	
	//用户登录请求
	@RequestMapping("/back/goLogin")
	@ResponseBody
	public Result goLogin(String userName,String passWord,String patchca,Boolean chkRememberMe,HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		//1.先验证验证码
		HttpSession session = request.getSession();
		try {
			if (!((String) session.getAttribute("captchaToken")).toLowerCase()
						.equals(patchca.toLowerCase())) {
					result.setStatus(3); // 验证码有误
					return result;
			}
		} catch (Exception e) {
			result.setStatus(3); // 验证码有误
			return result;
		}
		result =  loginService.goLogin(userName, passWord,chkRememberMe, true, request, response);
		
		
		//判断是否是后台管理员
		//1.代表后台管理员
//		Integer userType = (Integer)result.getData();
//		 if(userType !=null && userType!= 1){   //1.代表后台管理员
//			 result.setStatus(5);
//			 result.setMsg("该账号无法在该平台登录");
//		 }
		 return result;
	}
	
	
	//用户登录请求
	@RequestMapping("/front/goLogin")
	@ResponseBody
	public Result goLogin_front(String userName,String passWord,Boolean chkRememberMe,HttpServletRequest request,HttpServletResponse response){
		 Result result = loginService.goLogin(userName, passWord,chkRememberMe, false, request, response);
		/* try {
			 if((Integer)result.getData() != 2){   //2.代表学生
				 result.setStatus(5);
				 result.setMsg("该账号无法在该平台登录");
			 }
		} catch (Exception e) {
			
		}*/
		 return result;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Result logout(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		if(LoginUtil.logout(request,response)){
			result.setStatus(1);
		}else{
			result.setStatus(2);
		}
		return result;
	}
	
}
