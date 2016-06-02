package com.yunrong.service;

import java.util.List;

import com.yunrong.entity.LoginUser;
import com.yunrong.util.Pager;

public interface LoginUserService extends BaseService<LoginUser, String> {
	public List<LoginUser> findAllUser();

	public void saveUser(LoginUser user);

	public void deleteUser(String id);

	public void deleteUsers(String[] ids);

	public LoginUser queryUserById(String id);

	public void updateUser(LoginUser user);

	public Pager findUserByPage(Pager pager);
}
