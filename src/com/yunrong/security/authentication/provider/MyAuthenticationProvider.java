package com.yunrong.security.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.yunrong.security.authentication.exception.UsernameOrPasswordIsNull;
import com.yunrong.security.authentication.token.MyAuthenticationToken;

public class MyAuthenticationProvider extends DaoAuthenticationProvider
		implements AuthenticationProvider {

	@SuppressWarnings("deprecation")
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		MyAuthenticationToken authenticationToken = (MyAuthenticationToken) authentication;
		// 获得用户名
		if (authenticationToken.getPrincipal() == null
				|| "".equals(authenticationToken.getPrincipal().toString())) {
			throw new UsernameOrPasswordIsNull("username is null");
		}
		String username = authenticationToken.getName();
		// 获得的用户输入密码
		if (authentication.getCredentials() == null
				|| "".equals(authenticationToken.getCredentials().toString())) {
			throw new UsernameOrPasswordIsNull("password is null");
		}
		String presentedPassword = authenticationToken.getCredentials()
				.toString();
		System.out.println("用户密码:" + presentedPassword);
		// 获得的用户信息
		UserDetails userDetails = getUserDetailsService().loadUserByUsername(
				username);
		// 获得用户名，作为加密盐值
		Object salt = getSaltSource().getSalt(userDetails);
		String pass = getPasswordEncoder().encodePassword(username, salt);
		System.out.println("密码:" + pass);
		// 判断密码是否匹配
		if (!getPasswordEncoder().isPasswordValid(userDetails.getPassword(),
				presentedPassword, salt)) {
			throw new BadCredentialsException("password is error");
		}
		// 重新构建UsernamePasswordAuthenticationToken传递给决策管理器进行授权管理
		return new MyAuthenticationToken(userDetails,
				authentication.getPrincipal(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return MyAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
