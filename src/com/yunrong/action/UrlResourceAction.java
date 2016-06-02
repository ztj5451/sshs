package com.yunrong.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yunrong.entity.UrlResource;
import com.yunrong.service.UrlResourceService;

/**
 * 系统权限路径资源
 * 
 * @author Administrator
 * 
 */
@Controller
@Namespace("/url")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@Results({
		@Result(name = "urlList", location = "/WEB-INF/pages/urlResource/url_list.jsp"),
		@Result(name = "urlToAdd", location = "/WEB-INF/pages/urlResource/add_url.html"),
		@Result(name = "success", location = "/WEB-INF/pages/urlResource/ajaxDone.html"),
		@Result(name = "userAdd", location = "/WEB-INF/pages/common/ajaxDone.html"),
		@Result(name = "userDele", location = "/jsp/test2.jsp"),
		@Result(name = "queryOne", location = "/WEB-INF/pages/user/update_user.jsp") })
public class UrlResourceAction extends ActionSupport implements
		ModelDriven<UrlResource>, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707216181949023505L;
	@Resource(name="urlResourceService")
	private UrlResourceService urlResourceService;
	private UrlResource model = new UrlResource();
	private HttpServletRequest request;
	private List<UrlResource> urlResources;
	private String message;

	/**
	 * 列表
	 * 
	 * @return
	 */
	@Action(value = "list")
	public String urlResourceListAction() {
		urlResources=	urlResourceService.getAllUrlResource();
		JSONArray array = new JSONArray();
		for(UrlResource urlResource:urlResources)
		{
			JSONObject object=new JSONObject();
			object.put("id", urlResource.getId());
			object.put("parentId", urlResource.getParentId());
			object.put("urlName", urlResource.getUrlName());
			object.put("parentName", urlResource.getParentName());
			object.put("url", urlResource.getUrl());
			array.add(object);
		}
		message=array.toString();
		return "urlList";
	}

	@Action(value = "urlToAdd")
	public String urlToAddAction() {

		return "urlToAdd";
	}

	/**
	 * 新增
	 * 
	 * @return
	 */
	@Action(value = "addUrl")
	public String addUrlResourceAction() {
		urlResourceService.addUrlResource(model);
		return "success";
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	@Action(value = "updateUrl")
	public String updateUrlResourceAction() {
		urlResourceService.update(model);
		return "updateUrl";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "deleteUrl")
	public String deleteUrlResourceAction() {
		urlResourceService.deleteUrlResource(request.getParameter(""));
		return "deleteUrl";
	}

	public UrlResourceService getUrlResourceService() {
		return urlResourceService;
	}

	public void setUrlResourceService(UrlResourceService urlResourceService) {
		this.urlResourceService = urlResourceService;
	}

	public UrlResource getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

	public String getMessage() {
		return message;
	}

	public List<UrlResource> getUrlResources() {
		return urlResources;
	}

}
