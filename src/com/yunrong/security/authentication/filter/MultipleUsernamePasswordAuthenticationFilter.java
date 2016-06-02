package com.yunrong.security.authentication.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yunrong.util.CaptchaUtil;

public class MultipleUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	private MyAuthenticationTokenResolver tokenResolver;
	public static final String CAPTCHA = "captcha"; // 验证码

	/**
	 * 重写身份验证方法
	 */
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) {

		// 校验验证码
		checkValidateCode(request);
	
		// 获得用户凭证
		Authentication authentication = tokenResolver.resolve(request);
		//System.err.println(""+authentication.getDetails());
		// 验证用户凭证
		return this.getAuthenticationManager().authenticate(authentication);
		//throw new UnsupportedOperationException( "No authentication token resolver found!");
	}

	/**
	 * 使用工具类校验输入的验证码是否匹配
	 * 
	 * @param request
	 */
	protected void checkValidateCode(HttpServletRequest request) {
		if (!CaptchaUtil.validateCaptchaByRequest(request)) {
			throw new AuthenticationServiceException("ValidateCode Not Equals");
		}
	}

	public MyAuthenticationTokenResolver getTokenResolver() {
		return tokenResolver;
	}

	public void setTokenResolver(MyAuthenticationTokenResolver tokenResolver) {
		this.tokenResolver = tokenResolver;
	}

}
