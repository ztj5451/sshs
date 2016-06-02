package com.yunrong.security.authentication.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;

import com.yunrong.security.authentication.token.MyAuthenticationToken;


/**
 * 登陆凭证
 * 
 */
public class MyAuthenticationTokenResolver{

	private static final Logger logger = Logger
			.getLogger(MyAuthenticationTokenResolver.class);
	public static final String USERNAME = "username"; // 登陆用户名
	public static final String PASSWORD = "password"; // 登陆密码

	public Authentication resolve(HttpServletRequest request) {
		String username = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		if (logger.isDebugEnabled()) {
			logger.info("登陆的用户名：" + username);
		}
		return new MyAuthenticationToken(username, password);
	}
}
