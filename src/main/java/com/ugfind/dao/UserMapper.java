package com.ugfind.dao;

import com.ugfind.model.NewPwdPojo;
import com.ugfind.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User goLogin(User user);

	int checkUserTelphone(String telphone);

	int checkUserName(String userName);
	
	User getUserInfo(Integer id);

	int checkNickName(String nickName);

	int updatePwd(NewPwdPojo newPwdPojo);

	int updatePwdByCode(String userName, String passWord);
	
	/**
	 * 获取用户是否老师认证的信息
	 * @param id
	 * @return
	 */
	User getUserAttestationInfo(Integer id);
}