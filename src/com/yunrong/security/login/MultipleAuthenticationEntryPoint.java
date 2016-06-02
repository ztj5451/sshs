package com.yunrong.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


public class MultipleAuthenticationEntryPoint implements
		AuthenticationEntryPoint {

	/**
	 * 退出后跳转的url
	 */
	private String directUrl;
	
	public String getDirectUrl() {
		return directUrl;
	}

	public void setDirectUrl(String directUrl) {
		this.directUrl = directUrl;
	}


	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		// 跳转到登陆依据所绑定的登录页面
		response.sendRedirect(request.getContextPath() + directUrl);

	}


}
