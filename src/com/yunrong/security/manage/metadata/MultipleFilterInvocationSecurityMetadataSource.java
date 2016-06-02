package com.yunrong.security.manage.metadata;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MultipleFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private MySecurityMetadataSource metadataSource;

	public MySecurityMetadataSource getMetadataSource() {
		return metadataSource;
	}

	public void setMetadataSource(MySecurityMetadataSource metadataSource) {
		this.metadataSource = metadataSource;
	}

	// 返回所有权限属性的集合对象
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return metadataSource.getAllConfigAttributes();
	}

	// 接口中规定的方法， 这核心方法 ，用户获取正在访问的资源所对应的权限集合
	public Collection<ConfigAttribute> getAttributes(Object filter)
			throws IllegalArgumentException {
		return metadataSource.getAttributes(filter);
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
