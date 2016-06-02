package com.yunrong.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/test")
public class TestAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	HttpServletRequest request;
	HttpServletResponse response;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3362420032850327842L;

	@Action(value = "test")
	public void Auth() {
		String name = request.getParameter("name");
		System.out.println(name);
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

}
