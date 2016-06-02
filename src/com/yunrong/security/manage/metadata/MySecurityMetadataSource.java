package com.yunrong.security.manage.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;

import com.yunrong.entity.UrlSource;
import com.yunrong.service.UrlSourceService;

public class MySecurityMetadataSource implements SecurityMetadataSource {

	private static final Logger logger = Logger
			.getLogger(MySecurityMetadataSource.class);

	private List<UrlSource> resourceList;
	@Resource(name = "urlSourceService")
	private UrlSourceService urlSourceService;

	public UrlSourceService getUrlSourceService() {
		return urlSourceService;
	}

	public void setUrlSourceService(UrlSourceService urlSourceService) {
		this.urlSourceService = urlSourceService;
	}

	@PostConstruct
	public void init() {

		resourceList = urlSourceService.getAllList();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Collection<ConfigAttribute> collection = new ArrayList<ConfigAttribute>();
		for (UrlSource resURL : resourceList) {
			if (resURL.getConfigAttributes() != null) {
				collection.addAll(resURL.getConfigAttributes());
			}
		}
		return collection;
	}

	public Collection<ConfigAttribute> getAttributes(Object filter)
			throws IllegalArgumentException {

		Collection<ConfigAttribute> collection = new ArrayList<ConfigAttribute>();
		HttpServletRequest request = ((FilterInvocation) filter).getRequest();
		for (UrlSource resURL : resourceList) {
			if (resURL.matches(request)) {
				collection = resURL.getConfigAttributes();
				break;
			}
		}
		if (logger.isDebugEnabled()) {
			logger.info("访问的URL：" + ((FilterInvocation) filter).getRequestUrl());
		}
		return collection;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
