package com.yunrong.dao;

import java.util.List;

import com.yunrong.entity.Organization;

public interface OrganizationDao extends BaseDao<Organization, Integer> {
	public void addTree(Organization organization);

	public Integer addTreeForId(Organization organization);

	public List<Organization> getTreeList();

	public void updateParent(Organization organization);

	public Organization queryOrganization(int id);
}
