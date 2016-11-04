package com.ugfind.util;

import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ugfind.dao.UserMapper;
import com.ugfind.model.User;

/**
 * 控制用户登录状态
 * 
 * @author Administrator
 *
 */
public class LoginUtil {
	public static final String cookieStatus = "UGO2OLogin"; // 控制o2o登录状态

	@Resource
	private UserMapper userDao;
	
	public static User getUserInfo(HttpServletRequest request) {
		
		try {
			Cookie[] cookies = request.getCookies();

			Integer userId = null;
			String nickName = null;
			Integer schoolId = null;
			Integer userType = null;
			Integer deptId = null;
			String name = null;
			Integer attestationState = 0;

			if(cookies == null){
				return null;
			}
			
 			for (Cookie cookie : cookies) {
				if (LoginUtil.cookieStatus.equals(cookie.getName())) {
					nickName = URLDecoder.decode(cookie.getValue().split("\\|")[0],"utf-8");
					userId = Integer.parseInt(cookie.getValue().split("\\|")[1]);					
					schoolId = Integer.parseInt(cookie.getValue().split("\\|")[2]);
					userType = Integer.parseInt(cookie.getValue().split("\\|")[3]);
					name = URLDecoder.decode(cookie.getValue().split("\\|")[5], "utf-8");
					attestationState = Integer.parseInt(cookie.getValue().split("\\|")[6]);
					String str = cookie.getValue().split("\\|")[4];
					if("null".equals(str)){
						deptId = null;
					}else{
						deptId = Integer.parseInt(str);
					}
				}
			}
			
			if(nickName == null){
		    	  HttpSession session = request.getSession(false);
			      if(session != null && session.getAttribute("nickName") != null){
						nickName = (String)session.getAttribute("nickName");
						userId = (Integer) session.getAttribute("userId");
						schoolId = (Integer)session.getAttribute("schoolId");
						userType = (Integer)session.getAttribute("userType");
						deptId =(Integer)session.getAttribute("deptId");
						name = (String) session.getAttribute("name");
						attestationState = (Integer) session.getAttribute("attestationState");
				  }
		      }
			
			 if(nickName == null){
		    	  return null;
		      }else{
		    	  User user = new User();
		    	  user.setNickName(nickName);
		    	  user.setId(userId);
		    	  user.setSchoolId(schoolId);
		    	  user.setUserType(userType);
		    	  user.setDeptId(deptId);
		    	  user.setName(name);
		    	  user.setAttestationState(attestationState);
		    	  return user;
		      }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * 注销登录
	 * @return
	 */
	public static boolean logout(HttpServletRequest request,HttpServletResponse response){
		try {
			HttpSession session = request.getSession(false);
			session.invalidate();
			Cookie cookie = new Cookie(LoginUtil.cookieStatus, null); 
			cookie.setMaxAge(0);
			cookie.setPath("/");// 不要漏掉
			response.addCookie(cookie); 
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
