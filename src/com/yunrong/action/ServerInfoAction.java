package com.yunrong.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yunrong.entity.ServerStatus;
import com.yunrong.util.Common;
import com.yunrong.util.PropertiesUtils;

/**
 * 服务器信息
 * 
 * @author Administrator
 * 
 */
@Controller
@Namespace("/server")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
@Results({
		@Result(name = "info", location = "/WEB-INF/pages/server/serverInfo.jsp"),
		@Result(name = "warning", location = "/WEB-INF/pages/server/serverWarning.jsp"),
		@Result(name = "success", location = "/WEB-INF/pages/common/ajaxDone.html"),
		@Result(name = "userAdd", location = "/WEB-INF/pages/common/ajaxDone.html"),
		@Result(name = "userDele", location = "/jsp/test2.jsp"),
		@Result(name = "queryOne", location = "/WEB-INF/pages/user/update_user.jsp") })
public class ServerInfoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 887756452398403596L;
	private String message;

	/**
	 * 获取服务器基本信息
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "info")
	public String serverBaseInfo() throws Exception {
		ServerStatus status = Common.getServerStatus();
		JSONObject jsonObject = JSONObject.fromObject(status);
		message = jsonObject.toString();
		return "info";
	}

	/**
	 * 预警监控信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "warning")
	public String serverWarningInfo() throws Exception {
		ServerStatus status = Common.getServerStatus();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String cpuUsage = status.getCpuUsage();
		long FreeMem = status.getFreeMem();
		long useMem = status.getUsedMem();
		long TotalMem = status.getTotalMem();
		String serverUsage = Common.fromUsage(useMem, TotalMem);
		dataMap.put("cpuUsage", cpuUsage);
		dataMap.put("FreeMem", FreeMem);
		dataMap.put("TotalMem", TotalMem);
		dataMap.put("serverUsage", serverUsage);
		long JvmFreeMem = status.getJvmFreeMem();
		long JvmTotalMem = status.getJvmTotalMem();
		String JvmUsage = Common.fromUsage(JvmTotalMem - JvmFreeMem,
				JvmTotalMem);
		dataMap.put("JvmFreeMem", JvmFreeMem);
		dataMap.put("JvmTotalMem", JvmTotalMem);
		dataMap.put("JvmUsage", JvmUsage);
		dataMap.put("cpu", PropertiesUtils.findPropertiesKey("cpu"));
		dataMap.put("jvm", PropertiesUtils.findPropertiesKey("jvm"));
		dataMap.put("ram", PropertiesUtils.findPropertiesKey("ram"));
		dataMap.put("toEmail", PropertiesUtils.findPropertiesKey("toEmail"));
		dataMap.put("diskInfos", status.getDiskInfos());
		message = JSONObject.fromObject(status).toString();
		System.out.println("json:"+message);
		return "warning";
	}

	public String getMessage() {
		return message;
	}

}
