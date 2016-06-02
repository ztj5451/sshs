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
import com.yunrong.entity.LoginUser;
import com.yunrong.service.LoginUserService;
import com.yunrong.util.Pager;
import com.yunrong.util.TimeUtils;

/**
 * 用户管理Action
 * 
 * @author Administrator
 * 
 */
@Controller
@Namespace("/user")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@Results({
		@Result(name = "userList", location = "/WEB-INF/pages/user/user_list.jsp"),
		@Result(name = "userToAdd", location = "/WEB-INF/pages/user/add_user.html"),
		@Result(name = "success", location = "/WEB-INF/pages/common/ajaxDone.html"),
		@Result(name = "userAdd", location = "/WEB-INF/pages/common/ajaxDone.html"),
		@Result(name = "userDele", location = "/jsp/test2.jsp"),
		@Result(name = "queryOne", location = "/WEB-INF/pages/user/update_user.jsp") })
public class LoginUserAction extends ActionSupport implements
		ModelDriven<LoginUser>, ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8088834024053721947L;
	@Resource
	private LoginUserService loginUserService;
	private LoginUser loginUser;
	private LoginUser model = new LoginUser();
	private List<LoginUser> loginUsers;
	private HttpServletRequest request;
	private String message;

	public String getMessage() {
		return message;
	}

	public List<LoginUser> getLoginUsers() {
		return loginUsers;
	}

	public LoginUserService getLoginUserService() {
		return loginUserService;
	}

	public void setLoginUserService(LoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}

	// 登录用户列表
	@Action(value = "list")
	public String userListAction() {
		loginUsers = loginUserService.findAllUser();

		JSONArray array = new JSONArray();
		for (LoginUser loginUser : loginUsers) {
			JSONObject object = new JSONObject();
			object.put("id", loginUser.getId());
			object.put("userName", loginUser.getUsername());
			object.put("name", loginUser.getName());
			object.put("email", loginUser.getEmail());
			if (loginUser.getLoginDate() == null) {
				object.put("loginDate", "尚未登录");
			} else {
				object.put("loginDate",
						TimeUtils.getFullDateTime(loginUser.getLoginDate()));
			}

			object.put("loginIp", loginUser.getLoginIp());

			if (loginUser.isUserAccountNonExpired()) {
				object.put("userAccountNonExpired", "是");
			} else {
				object.put("userAccountNonExpired", "否");
			}
			if (loginUser.isUserAccountNonLocked()) {
				object.put("userAccountNonLocked", "是");
			} else {
				object.put("userAccountNonLocked", "否");
			}
			if (loginUser.isUserEnabled()) {
				object.put("userEnabled", "是");
			} else {
				object.put("userEnabled", "否");
			}

			array.add(object);
		}
		message = array.toString();
		System.out.println(loginUsers.size());
		System.out.println("执行该方法 list");
		return "userList";
	}

	// 登录用户列表
	@Action(value = "list2")
	public String userList2Action() {
		String status = request.getParameter("status");
		String keywords = request.getParameter("keywords");
		String pageNum = request.getParameter("pageNum");
		String numPerPage = request.getParameter("numPerPage");
		String orderField = request.getParameter("orderField");
		Pager pager = new Pager();

		Pager pager2 = loginUserService.findUserByPage(pager);
		System.out.println("fdsf" + pager2.getPageCount());

		
		loginUsers = loginUserService.findAllUser();

		JSONArray array = new JSONArray();
		for (LoginUser loginUser : loginUsers) {
			JSONObject object = new JSONObject();
			object.put("id", loginUser.getId());
			object.put("userName", loginUser.getUsername());
			object.put("name", loginUser.getName());
			object.put("email", loginUser.getEmail());
			if (loginUser.getLoginDate() == null) {
				object.put("loginDate", "尚未登录");
			} else {
				object.put("loginDate",
						TimeUtils.getFullDateTime(loginUser.getLoginDate()));
			}

			object.put("loginIp", loginUser.getLoginIp());

			if (loginUser.isUserAccountNonExpired()) {
				object.put("userAccountNonExpired", "是");
			} else {
				object.put("userAccountNonExpired", "否");
			}
			if (loginUser.isUserAccountNonLocked()) {
				object.put("userAccountNonLocked", "是");
			} else {
				object.put("userAccountNonLocked", "否");
			}
			if (loginUser.isUserEnabled()) {
				object.put("userEnabled", "是");
			} else {
				object.put("userEnabled", "否");
			}

			array.add(object);
		}
		message = array.toString();
		System.out.println(loginUsers.size());
		System.out.println("执行该方法 list");
		
		return "userList";
	}

	// 新增用户跳转
	@Action(value = "toAdd")
	public String userToAddAction() {
		System.out.println("执行该方法 add");
		return "userToAdd";
	}

	// 新增用户
	@Action(value = "userAdd")
	public String userAddAction() {
		loginUserService.saveUser(model);

		return "userAdd";
	}

	// 删除用户
	@Action(value = "dele")
	public String userDeleAction() {
		System.out.println("执行该方法 delete");
		String userId = request.getParameter("uid");
		System.out.println("删除用户的Id:" + userId);
		loginUserService.deleteUser(userId);
		return "success";
	}

	// 删除多个用户
	@Action(value = "deleMany")
	public String userDeleManyAction() {

		String[] ids = request.getParameter("ids").split(",");
		for (int i = 0; i < ids.length; i++) {
			System.out.println("删除用户的Ids:" + ids[i]);
		}

		loginUserService.deleteUsers(ids);
		return "success";
	}

	// 查找用户
	@Action(value = "queryOne")
	public String queryOneUserAction() {
		String userId = request.getParameter("uid");
		System.out.println("获取的userId:"+userId);
		loginUser = loginUserService.queryUserById(userId);
		JSONObject object = new JSONObject();
		object.put("id", loginUser.getId());
		object.put("userName", loginUser.getUsername());
		object.put("name", loginUser.getName());
		object.put("email", loginUser.getEmail());
		message = object.toString();
		return "queryOne";
	}

	// 更新用户
	@Action(value = "userUpdate")
	public String userUpdateAction() {
		LoginUser user = model;
		loginUserService.updateUser(model);
		return "success";
	}

	public LoginUser getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;

	}

}
