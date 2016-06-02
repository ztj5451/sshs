package com.yunrong.dao.impl;

import org.springframework.stereotype.Repository;

import com.yunrong.dao.UrlSourceDao;
import com.yunrong.dao.impl.BaseDaoImpl;
import com.yunrong.entity.UrlSource;

@Repository("urlSourceDao")
public class UrlSourceDaoImpl extends BaseDaoImpl<UrlSource, String> implements
		UrlSourceDao {

}