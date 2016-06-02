package com.yunrong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrong.dao.UrlResourceDao;
import com.yunrong.entity.UrlResource;
import com.yunrong.service.UrlResourceService;
@Transactional
@Service("urlResourceService")
public class UrlResourceServiceImpl extends
		BaseServiceImpl<UrlResource, String> implements UrlResourceService {
	@Resource(name = "urlResourceDao")
	private UrlResourceDao urlResourceDao;

	public UrlResourceDao getUrlResourceDao() {
		return urlResourceDao;
	}

	public void setUrlResourceDao(UrlResourceDao urlResourceDao) {
		this.urlResourceDao = urlResourceDao;
	}

	public void addUrlResource(UrlResource urlResource) {
		// TODO Auto-generated method stub
		urlResourceDao.addUrlResource(urlResource);

	}

	public void updateUrlResource(UrlResource urlResource) {
		// TODO Auto-generated method stub
		urlResourceDao.updateUrlResource(urlResource);
	}

	public void deleteUrlResource(String id) {
		// TODO Auto-generated method stub
		urlResourceDao.deleteUrlResource(id);

	}

	public List<UrlResource> getAllUrlResource() {
		// TODO Auto-generated method stub
		return urlResourceDao.getAllUrlResource();

	}
}
