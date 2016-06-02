package com.yunrong.dao;

import com.yunrong.entity.LoginUser;
/**
 * 根据用户名进行登录用户查找
 * @author Administrator
 *
 */
public interface LoginDao extends BaseDao<LoginUser,String> {
	/**
	 * 根据用户名，获取管理员（不区分大小写）
	 * 
	 * @param userName
	 * @return
	 */
	public LoginUser findUserByName(String username);
}