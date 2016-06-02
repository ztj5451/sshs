package com.yunrong.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunrong.dao.UrlSourceDao;
import com.yunrong.entity.UrlSource;
import com.yunrong.service.UrlSourceService;

@Service("urlSourceService")
public class UrlSourceServiceImpl extends BaseServiceImpl<UrlSource, String>
		implements UrlSourceService {

	@Resource(name = "urlSourceDao")
	private UrlSourceDao urlSourceDao;

	public UrlSourceDao getUrlSourceDao() {
		return urlSourceDao;
	}

	@Resource(name = "urlSourceDao")
	public void setUrlSourceDao(UrlSourceDao urlSourceDao) {
		super.setBaseDao(urlSourceDao);
	}

}