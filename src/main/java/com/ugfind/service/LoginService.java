package com.ugfind.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ugfind.model.Result;

@Service
public interface LoginService {

	//用户登录验证
	public abstract Result goLogin(String userName, String passWord,
			Boolean chkRememberMe, Boolean isBack, HttpServletRequest request,
			HttpServletResponse response);

}