package com.yunrong.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yunrong.dao.OrganizationDao;
import com.yunrong.entity.Organization;

@Repository("organizationDao")
public class OrganizationDaoImpl extends BaseDaoImpl<Organization, Integer>
		implements OrganizationDao {

	public void addTree(Organization organization) {
		// TODO Auto-generated method stub
		save(organization);

	}

	public List<Organization> getTreeList() {
		// TODO Auto-generated method stub

		return getAllList();
	}

	public void updateParent(Organization organization) {
		// TODO Auto-generated method stub
		update(organization);

	}

	public Organization queryOrganization(int id) {
		// TODO Auto-generated method stub
		String HQL = "FROM Organization as o WHERE o.id = ?";
		return uniqueResult(HQL, id);
	}

	public Integer addTreeForId(Organization organization) {
		// TODO Auto-generated method stub
		return save(organization);
	}

}
