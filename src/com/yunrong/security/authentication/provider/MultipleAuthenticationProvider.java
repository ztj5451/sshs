package com.yunrong.security.authentication.provider;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MultipleAuthenticationProvider implements AuthenticationProvider {

	private List<AuthenticationProvider> authenticationProviders;

	// 根据登录前的用户凭证筛选匹配的Provider,并构建新的用户凭证供登陆后的决策器调用

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		Authentication authenticationToken = null;
		for (AuthenticationProvider authenticationProvider : authenticationProviders) {
			if (authenticationProvider.supports(authentication.getClass())) {
				authenticationToken = authenticationProvider
						.authenticate(authentication);
			}
		}
		return authenticationToken;
	}

	public boolean supports(Class<? extends Object> authentication) {
		return true;
	}

	public List<AuthenticationProvider> getAuthenticationProviders() {
		return authenticationProviders;
	}

	public void setAuthenticationProviders(
			List<AuthenticationProvider> authenticationProviders) {
		this.authenticationProviders = authenticationProviders;
	}
}
