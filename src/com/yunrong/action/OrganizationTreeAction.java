package com.yunrong.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yunrong.entity.Organization;
import com.yunrong.service.OrganizationService;

@Controller
@Namespace("/tree")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@Results({
		@Result(name = "treeList", location = "/WEB-INF/pages/tree/organization_tree.jsp"),
		@Result(name = "treeToAdd", location = "/WEB-INF/pages/tree/add_tree.jsp"),
		@Result(name = "treeAdd", location = "/WEB-INF/pages/tree/ajaxDone.html"),
		@Result(name = "treeUpdate", location = "/WEB-INF/pages/tree/update_tree.jsp"),
		@Result(name = "treeDel", location = "/WEB-INF/pages/tree/del_tree.jsp") })
public class OrganizationTreeAction extends ActionSupport implements
		ModelDriven<Organization> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8467811324778272396L;
	@Resource
	private OrganizationService organizationService;
	private Organization model = new Organization();
	private String message;
	private List<Organization> organizations;
	private Organization organization;

	public String getMessage() {
		return message;
	}

	@Action(value = "list")
	public String treeListAction() {
		organizations = organizationService.getTreeList();
		organization = organizationService.queryOrganization(1);
		JSONArray array = new JSONArray();
		for (Organization organization : organizations) {
			JSONObject object = new JSONObject();
			object.put("id", organization.getId());
			object.put("pid", organization.getPid());
			object.put("ids", organization.getIds());
			object.put("name", organization.getName());
			List<Organization> list = new ArrayList<Organization>();
			if (organization.getIds().length() != 0) {
				JSONObject obj = new JSONObject();
				// obj.put("id", value)

				// object.put("child", value)

			}
			array.add(object);
		}
		message = array.toString();
		System.out.println(message);
		return "treeList";
	}

	// 新增组织架构跳转
	@Action(value = "toAdd")
	public String treeToAddAction() {
		System.out.println("执行该方法 add");
		organizations = organizationService.getTreeList();
		JSONArray array = new JSONArray();
		for (Organization organization : organizations) {
			JSONObject object = new JSONObject();
			object.put("id", organization.getId());
			object.put("pid", organization.getPid());
			object.put("ids", organization.getIds());
			object.put("name", organization.getName());
			array.add(object);
		}
		message = array.toString();

		return "treeToAdd";
	}

	// 修改组织架构跳转
	@Action(value = "toUpdate")
	public String treeToUpdateAction() {
		System.out.println("执行该方法 add");
		organizations = organizationService.getTreeList();
		JSONArray array = new JSONArray();
		for (Organization organization : organizations) {
			JSONObject object = new JSONObject();
			object.put("id", organization.getId());
			object.put("pid", organization.getPid());
			object.put("ids", organization.getIds());
			object.put("name", organization.getName());
			array.add(object);
		}
		message = array.toString();

		return "toUpdate";
	}

	// 删除组织架构跳转
	@Action(value = "toDel")
	public String treeToDelAction() {
		System.out.println("执行该方法 add");
		organizations = organizationService.getTreeList();
		JSONArray array = new JSONArray();
		for (Organization organization : organizations) {
			JSONObject object = new JSONObject();
			object.put("id", organization.getId());
			object.put("pid", organization.getPid());
			object.put("ids", organization.getIds());
			object.put("name", organization.getName());
			array.add(object);
		}
		message = array.toString();

		return "treeToDel";
	}

	// 新增组织架构
	@Action(value = "treeAdd")
	public String treeAddAction() {
		int id = organizationService.addTreeForId(model);
		int tempId = model.getPid();
		Organization tempOrganization = organizationService
				.queryOrganization(tempId);
		String tempIds = tempOrganization.getIds() + "," + id;
		tempOrganization.setIds(tempIds);
		organizationService.updateParent(tempOrganization);
		System.out.println("执行该方法 treeAdd");
		return "treeAdd";
	}

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Organization getModel() {
		// TODO Auto-generated method stub
		return model;
	}
}
