package com.yunrong.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.yunrong.dao.LoginUserDao;
import com.yunrong.entity.LoginUser;
import com.yunrong.util.Pager;

/**
 * 登录用户Dao 接口实现
 * 
 * @author Administrator
 * 
 */
@Repository("userDao")
public class LoginUserDaoImpl extends BaseDaoImpl<LoginUser, String> implements
		LoginUserDao {
	/**
	 * 保存登录用户对象
	 */
	public void addUser(LoginUser loginUser) {
		// TODO Auto-generated method stub
		save(loginUser);

	}

	// 查询登录用户对象
	public List<LoginUser> allUser() {
		// TODO Auto-generated method stub

		String HQL = "FROM LoginUser";
		return find(HQL);
	}

	/**
	 * 根据用户名查询用户
	 */
	public LoginUser querySingleLoginUser(String userName) {
		// TODO Auto-generated method stub
		String HQL = "FROM LoginUser as t WHERE t.username = ?";
		return uniqueResult(HQL, userName);
	}

	/**
	 * 根据用户id 删除指定的记录
	 */
	public void deleteUserById(String id) {
		// TODO Auto-generated method stub
		delete(id);

	}

	public void deleteUserById(String[] ids) {
		// TODO Auto-generated method stub
		delete(ids);

	}

	public LoginUser queryUserById(String id) {
		// TODO Auto-generated method stub
		return get(id);
	}

	public void updateUser(LoginUser user) {
		// TODO Auto-generated method stub
		update(user);

	}

	public Pager findUserByPage(Pager pager) {
		// TODO Auto-generated method stub
		return findPager(pager);
	}

}
