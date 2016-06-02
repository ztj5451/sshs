package com.yunrong.service;

import java.util.List;

import com.yunrong.entity.Organization;

public interface OrganizationService extends BaseService<Organization, Integer> {
	public void addTree(Organization organization);
	public List<Organization> getTreeList();
	public void updateParent(Organization organization);
	public Organization  queryOrganization(int id);
	public Integer addTreeForId(Organization organization);
}
