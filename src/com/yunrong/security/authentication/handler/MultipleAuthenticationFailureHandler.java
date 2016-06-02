package com.yunrong.security.authentication.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

public class MultipleAuthenticationFailureHandler extends
		ExceptionMappingAuthenticationFailureHandler {
	private String directUrl;

	public String getDirectUrl() {
		return directUrl;
	}

	public void setDirectUrl(String directUrl) {
		this.directUrl = directUrl;
	}

	//验证失败执行的方法，跳转到失败的URL
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		//添加跳转的URL
		setDefaultFailureUrl(directUrl);
		super.onAuthenticationFailure(request, response, exception);
	}

}
