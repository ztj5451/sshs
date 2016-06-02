package com.yunrong.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 * 用户登录验证Action
 * 
 * @author Administrator
 * 
 */
@Controller
@ParentPackage("login")
@Results({ @Result(name = "index", location = "/WEB-INF/pages/index.jsp"),
		@Result(name = "logout", location = "/login.jsp"),
		@Result(name = "anonymous", location = "/login.jsp"),
		@Result(name = "goLogin", location = "/login.jsp") })
public class IndexAction extends BaseAction {

	private static final long serialVersionUID = -1754357748424168916L;

	// 登录成功进入主页面
	@Action(value = "index")
	public String index() {
		return "index";
	}

	// 匿名用户进入登录页面
	@Action(value = "anonymous")
	public String anonymous() {
		return "anonymous";
	}

	// 登录失败进入登录页面
	@Action(value = "loginError")
	public String goLogin() {
		return "goLogin";
	}

	// 退出
	// @Action(value = "logout")
	// public String logout() {
	// System.out.println("logout!");
	// return "logout";
	// }

}
