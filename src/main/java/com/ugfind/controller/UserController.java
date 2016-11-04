package com.ugfind.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugfind.model.NewPwdPojo;
import com.ugfind.model.Result;
import com.ugfind.model.User;
import com.ugfind.service.UserService;
import com.ugfind.util.LoginUtil;
import com.ugfind.util.MD5Util;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 获取个人信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public Result getUserInfo(Integer id){
		Result result = new Result();
		User user = userService.getUserInfo(id);
		result.setData(user);
		return result;
	}
	
	/**
	 * 修改个人信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateUserInfo")
	@ResponseBody
	public Result updateUserInfo(User user){
		Result result = new Result();
		try {
			userService.updateByPrimaryKeySelective(user);
			//更新cookie 和 session
			
			result.setStatus(1);
		} catch (Exception e) {
			result.setStatus(2);
		}
		return result;
	}
	
	//更新cookie 和session信息
	@RequestMapping("/updateLoginStatus")
	public void updateLoginStatus(HttpServletRequest request,HttpServletResponse response,Integer userId){
		User user = userService.getUserById(userId);
		HttpSession session = request.getSession(false);
		//存session
		session.setMaxInactiveInterval(60 * 30); // 存30分钟
		session.setAttribute("nickName",user.getNickName());
		session.setAttribute("userId",user.getId());
		session.setAttribute("schoolId",user.getSchoolId());
		session.setAttribute("userType",user.getUserType());
		session.setAttribute("deptId", user.getDeptId());
		session.setAttribute("name", user.getName());
		session.setAttribute("attestationState", user.getAttestationState());
		//存cookie
		try {
			Cookie cookie = new Cookie(LoginUtil.cookieStatus,
					URLEncoder.encode(user.getNickName(), "utf-8")+"|"+
					user.getId()+"|"+
					user.getSchoolId()+"|"+
					user.getUserType()+"|"+
					user.getDeptId()+"|"+
					URLEncoder.encode(user.getName() == null ? "" : user.getName(), "utf-8")+"|"+
					user.getAttestationState()
			);
			cookie.setPath("/");
			// 存2年
			cookie.setMaxAge(60 * 60 * 24 * 30*24);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("updatePassWord")
	@ResponseBody
	public Result updatePassWord(HttpServletRequest request){
		Result result = new Result();
		String oldPass = request.getParameter("oldPass");
		String oldPass1 = MD5Util.string2MD5("key"+request.getParameter("oldPass1"));
		String newPass = MD5Util.string2MD5("key"+request.getParameter("newPass"));
		Integer id = Integer.valueOf(request.getParameter("id"));
		if(oldPass1.equals(oldPass)){
			User user= new User();
			user.setId(id);
			user.setPassWord(newPass);
			try {
				userService.updateByPrimaryKeySelective(user);
				result.setStatus(1);
				result.setMsg("修改成功");
			} catch (Exception e) {
				result.setStatus(2);
				result.setMsg("网络错误，请稍后重试");
			}
		}else{
			result.setStatus(2);
			result.setMsg("原始密码不匹配");
		}
		return  result;
	}
	
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Result updatePwd(NewPwdPojo newPwdPojo){
		Result result = new Result();
		if(userService.updatePwd(newPwdPojo) > 0){
			 result.setResult(1, null, null);
		}else{
			 result.setResult(2, null, "修改失败,请检查原始密码是否正确?");
		}
		return result;
	}
	
	
	 
}
