package com.yunrong.security.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yunrong.security.authentication.exception.UsernameOrPasswordIsNull;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginFailureInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -8095826836622599229L;
	private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String message = null;
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 返回Spring Security返回的异常
		Exception springSecurityException = (Exception) session.get(SPRING_SECURITY_LAST_EXCEPTION);
		// 判断异常类型，返回对应的提示信息
		if (springSecurityException != null) {
			if (springSecurityException instanceof AuthenticationServiceException) {
				message = "验证码错误,请重新输入!";
			} else if (springSecurityException instanceof UsernameOrPasswordIsNull) {
				message = "您的用户名或密码为空!";
			} else if (springSecurityException instanceof UsernameNotFoundException) {
				message = "您的用户名或密码错误!";
			} else if (springSecurityException instanceof BadCredentialsException) {
				message = "您的用户名或密码错误!";
			} else if (springSecurityException instanceof AccountExpiredException) {
				message = "您的账号已过期,无法登录!";
			} else if (springSecurityException instanceof LockedException) {
				message = "您的账号已被锁定,无法登录!";
			} else if (springSecurityException instanceof DisabledException) {
				message = "您的账号已被禁用,无法登录!";
			} else {
				message = "出现未知错误,无法登录!";
			}
			request.setAttribute("topMessage", message);
			//session.put("topMessage", message);
		}
		return invocation.invoke();
		//return "login";
	}
}
