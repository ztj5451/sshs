package com.yunrong.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class MultipleLogoutSuccessHandler implements LogoutSuccessHandler {

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

	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		response.sendRedirect(request.getContextPath() + directUrl);
		// throw new UnsupportedOperationException(
		// "No login success url resolver found!");

	}

}
