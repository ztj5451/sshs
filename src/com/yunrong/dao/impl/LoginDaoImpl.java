package com.yunrong.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunrong.dao.LoginDao;
import com.yunrong.entity.LoginUser;

@Repository("loginDao")
public class LoginDaoImpl extends BaseDaoImpl<LoginUser, String> implements LoginDao{
	
	public LoginUser findUserByName(String username){
		String HQL = "FROM LoginUser as t WHERE t.username = ?";
		return uniqueResult(HQL, username);
	}
}