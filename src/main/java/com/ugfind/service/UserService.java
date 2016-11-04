package com.ugfind.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ugfind.model.NewPwdPojo;
import com.ugfind.model.User;

@Service
public interface UserService {

	public abstract User getUserById(Integer id);

	Boolean checkUserTelphone(String telphone);

	public abstract boolean checkUserName(String userName);

	public abstract int insertUser(User user);
	
	User getUserInfo(Integer id);
	int updateByPrimaryKeySelective(User record);

	public abstract boolean checkNickName(String userName);

	public abstract int updatePwd(NewPwdPojo newPwdPojo);

	public abstract int updatePwdByCode(HttpSession session, String userName, String passWord,
			String code);
	/**
	 * 获取用户是否老师认证的信息
	 * @param id
	 * @return
	 */
	User getUserAttestationInfo(Integer id);
}