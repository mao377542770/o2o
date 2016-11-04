package com.ugfind.serviceImpl;

import javax.annotation.Resource;








import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.ugfind.dao.UserMapper;
import com.ugfind.model.NewPwdPojo;
import com.ugfind.model.User;
import com.ugfind.service.UserService;
import com.ugfind.util.MD5Util;

@Repository
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userDao;
	
	@Override
	public User getUserById(Integer id){
		return userDao.selectByPrimaryKey(id);
	}
	
	@Override
	public Boolean checkUserTelphone(String telphone){
		if(userDao.checkUserTelphone(telphone) > 0){
			return false;  //占用
		}else{
			return true;
		}
	}

	@Override
	public boolean checkUserName(String userName) {
		if(userDao. checkUserName(userName) > 0){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public boolean checkNickName(String nickName) {
		if(userDao.checkNickName(nickName) > 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public int insertUser(User user) {
		return userDao.insertSelective(user);
	}

	@Override
	public User getUserInfo(Integer id) {
		return userDao.getUserInfo(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updatePwd(NewPwdPojo newPwdPojo) {
		newPwdPojo.setOldPassWord(MD5Util.string2MD5("key"+newPwdPojo.getOldPassWord()));
		newPwdPojo.setPassWord(MD5Util.string2MD5("key"+newPwdPojo.getPassWord()));
		return userDao.updatePwd(newPwdPojo);
	}

	@Override
	public int updatePwdByCode(HttpSession session, String userName,
			String passWord, String code) {
		String oldCode = (String) session.getAttribute("changPwdCode");
		if(oldCode.equals(code)){
			return userDao.updatePwdByCode(userName,MD5Util.string2MD5("key"+passWord));
		}
		return 0;
	}

	@Override
	public User getUserAttestationInfo(Integer id) {
		return userDao.getUserAttestationInfo(id);
	}


}
