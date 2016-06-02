package com.yunrong.security.manage.decide;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MultipleAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 判断用户所拥有的角色是否有访问当前请求资源的权限
	 * 
	 * @author ZYL
	 */

	private static final Logger logger = Logger
			.getLogger(MultipleAccessDecisionManager.class);

	public void decide(Authentication authentication, Object filter,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		// 判断当前访问的资源
		if (logger.isDebugEnabled()) {
			logger.info("请求的用户：" + authentication.getName());
		}

		// 遍历访问该资源的所需权限属性
		for (ConfigAttribute attribute : configAttributes) {
			// 遍历访问用户当前的权限属性
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				System.out.println("用户权限:"+authority.getAuthority());
				if (attribute.getAttribute().equals(authority.getAuthority())) {
					if (logger.isDebugEnabled()) {
						logger.info("当前用户的" + authority.getAuthority()
								+ "角色满足访问条件");
					}
					return;
				}
			}
		}
		// 不满足访问条件抛出异常
		throw new AccessDeniedException("没有权限访问！");
		// throw new InsufficientAuthenticationException("用户登陆失败！");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
