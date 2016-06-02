package com.yunrong.security.authentication.token;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * 构建登陆的用户凭证(用户密码)
 * 
 * @author anywgte
 * 
 */

public class MyAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -1080396871514171817L;

	public MyAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public MyAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
