package com.yunrong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrong.dao.LoginUserDao;
import com.yunrong.entity.LoginUser;
import com.yunrong.service.LoginUserService;
import com.yunrong.util.Pager;

@Transactional
@Service("loginUserService")
public class LoginUserServiceImpl extends BaseServiceImpl<LoginUser, String>
		implements LoginUserService {
	@Resource(name = "userDao")
	private LoginUserDao userDao;

	public LoginUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(LoginUserDao userDao) {
		this.userDao = userDao;
	}

	public List<LoginUser> findAllUser() {
		// TODO Auto-generated method stub

		return userDao.allUser();
	}

	public void saveUser(LoginUser user) {
		// TODO Auto-generated method stub
		userDao.save(user);

	}

	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		userDao.delete(id);

	}

	public void deleteUsers(String[] ids) {
		// TODO Auto-generated method stub
		userDao.deleteUserById(ids);
	}

	public LoginUser queryUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.queryUserById(id);
	}

	public void updateUser(LoginUser user) {
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}

	public Pager findUserByPage(Pager pager) {
		// TODO Auto-generated method stub
		return userDao.findUserByPage(pager);
	}

}
