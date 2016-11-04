package com.ugfind.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.ugfind.dao.SchooldeptMapper;
import com.ugfind.dao.UserMapper;
import com.ugfind.model.Result;
import com.ugfind.model.SchoolServiceInfo;
import com.ugfind.model.User;
import com.ugfind.service.LoginService;
import com.ugfind.util.LoginUtil;
import com.ugfind.util.MD5Util;

@Repository
public class LoginServiceImpl implements LoginService {
	@Resource
	private Result result;
	@Resource
	private UserMapper userDao;
	@Resource
	private SchooldeptMapper schoolDeptMapper;
	
	//用户登录验证
	/* (non-Javadoc)
	 * @see com.ugfind.serviceImpl.LoginService#goLogin(java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Result goLogin(String userName,String passWord,Boolean chkRememberMe, Boolean isBack,HttpServletRequest request,HttpServletResponse response){
		 
		User user = new User();
		user.setUserName(userName);
		user.setPassWord(MD5Util.string2MD5("key" + passWord));
		//登录,成功则返回详细用户信息
		//判断用户是否有效(已在sql判断)，以及用户所属部门是否到期
		user = userDao.goLogin(user);
		if(user ==null){
			//没有找到该用户
			result.setStatus(2); // 账号或密码错误
			return result;
		}
		
		if(isBack == null){
			isBack = false;
		}
		
		if(isBack){
			Integer userType = user.getUserType() == null ? 0 : user.getUserType();
			if(userType != 1){   //1.代表后台管理员
				 result.setStatus(5);
				 result.setMsg("该账号无法在该平台登录");
				 return result;
			 }
			
			//用户所属部门是否到期
			SchoolServiceInfo service = schoolDeptMapper.getSchoolServiceInfoByUserId(user.getId());
			if(service == null || service.getServiceTime().getTime() < (new Date()).getTime()){
				//该部门未开通或到期
				result.setStatus(6);
				return result;
			}
			
		}
		
		HttpSession session = request.getSession();
		//存session
		session.setMaxInactiveInterval(60 * 30); // 存30分钟
		session.setAttribute("nickName",user.getNickName());
		session.setAttribute("userId",user.getId());
		session.setAttribute("schoolId",user.getSchoolId());
		session.setAttribute("userType",user.getUserType());
		session.setAttribute("deptId", user.getDeptId());
		session.setAttribute("name", user.getName());
		session.setAttribute("attestationState", user.getAttestationState());
		if(chkRememberMe == null){
			chkRememberMe = true;
		}
		if(chkRememberMe){
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
				//System.out.println(URLDecoder.decode(cookie.getValue().split("\\|")[5], "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}		
		result.setStatus(1);
//		result.setData(user.getUserType());   //data中存一个userType  用于限制登录
		return result;
	}
}
