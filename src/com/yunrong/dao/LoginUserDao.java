package com.yunrong.dao;

import java.util.List;
import com.yunrong.entity.LoginUser;
import com.yunrong.util.Pager;

/**
 * 登录用户Dao 接口
 * 
 * @author Administrator
 * 
 */
public interface LoginUserDao extends BaseDao<LoginUser, String> {
	/**
	 * 获取到所有登录用户
	 * 
	 * @return
	 */
	public List<LoginUser> allUser();

	/**
	 * 添加登录用户
	 * 
	 * @param loginUser
	 */
	public void addUser(LoginUser loginUser);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public LoginUser querySingleLoginUser(String userName);

	/**
	 * 根据用户Id查找用户
	 * 
	 * @param id
	 * @return
	 */
	public LoginUser queryUserById(String id);

	/**
	 * 根据用户Id进行删除用户
	 * 
	 * @param id
	 */
	public void deleteUserById(String id);

	/**
	 * 根据用户id进行删除多个用户
	 * 
	 * @param ids
	 */
	public void deleteUserById(String[] ids);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void updateUser(LoginUser user);

	/**
	 * 分页查找登录用户
	 * 
	 * @param pager
	 * @return
	 */
	public Pager findUserByPage(Pager pager);
}
