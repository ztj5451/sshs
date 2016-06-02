package com.yunrong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrong.dao.OrganizationDao;
import com.yunrong.entity.Organization;
import com.yunrong.service.OrganizationService;

@Transactional
@Service("organizationService")
public class OrganizationServiceImpl extends
		BaseServiceImpl<Organization, Integer> implements OrganizationService {
	@Resource(name = "organizationDao")
	private OrganizationDao organizationDao;

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void addTree(Organization organization) {
		// TODO Auto-generated method stub
		organizationDao.addTree(organization);

	}

	public List<Organization> getTreeList() {
		// TODO Auto-generated method stub

		return organizationDao.getTreeList();
	}

	public void updateParent(Organization organization) {
		// TODO Auto-generated method stub
		organizationDao.addTree(organization);

	}

	public Organization queryOrganization(int id) {
		// TODO Auto-generated method stub
		return organizationDao.queryOrganization(id);
	}

	public Integer addTreeForId(Organization organization) {
		// TODO Auto-generated method stub
		return organizationDao.addTreeForId(organization);

	}

}
