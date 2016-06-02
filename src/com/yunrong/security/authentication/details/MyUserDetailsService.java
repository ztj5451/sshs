package com.yunrong.security.authentication.details;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.yunrong.dao.LoginDao;
import com.yunrong.entity.LoginUser;

public class MyUserDetailsService implements UserDetailsService {

	@Resource(name = "loginDao")
	private LoginDao loginDao;

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	// 因为user实现了userDatils 接口，所以就可以直接返回user
	// 在这里必须启用事务管理,否则会导致session提前关闭
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		LoginUser user = loginDao.findUserByName(username);
		if (user == null) {
			throw new BadCredentialsException("UserName is Error");
		}
		
		return user;
	}

	// 获取用户权限集合，权限使用用GrantedAuthority表示，框架中 有他的实现类
	// GrantedAuthorityImpl 只需把角色名称放入即可
	public Collection<GrantedAuthority> getGrantedAuthorities(User user) {
		return user.getAuthorities();
	}
	
}
