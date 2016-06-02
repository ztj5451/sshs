package com.yunrong.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunrong.dao.UrlResourceDao;
import com.yunrong.entity.UrlResource;

@Repository("urlResourceDao")
public class UrlResourceDaoImpl extends BaseDaoImpl<UrlResource, String>
		implements UrlResourceDao {

	public void addUrlResource(UrlResource urlResource) {
		// TODO Auto-generated method stub
		save(urlResource);

	}

	public void updateUrlResource(UrlResource urlResource) {
		// TODO Auto-generated method stub

	}

	public void deleteUrlResource(String id) {
		// TODO Auto-generated method stub
		delete(id);

	}

	public List<UrlResource> getAllUrlResource() {
		// TODO Auto-generated method stub

		return getAllList();
	}

}
