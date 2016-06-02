package com.yunrong.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunrong.dao.LoginUserDao;
import com.yunrong.entity.LoginUser;
import com.yunrong.service.UserService;

/**
 * 登录用户service 接口实现
 * 
 * @author Administrator
 * 
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<LoginUser, String>
		implements UserService {
	@Resource(name = "userDao")
	private LoginUserDao userDao;

	public LoginUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(LoginUserDao userDao) {
		this.userDao = userDao;
	}

}
