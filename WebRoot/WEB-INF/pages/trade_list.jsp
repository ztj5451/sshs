<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="trade_transOne.action"
	class="pageForm required-validate"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td >交易订单号：<input type="text" name="orderNO" class="required" alt="请输入查询订单号"  type="text" size="35"/></td>
					<!--  
					<td><select class="combox" name="province">
							<option value="">所有省市</option>
							<option value="北京">北京</option>
							<option value="上海">上海</option>
							<option value="天津">天津</option>
							<option value="重庆">重庆</option>
							<option value="广东">广东</option>
					</select></td>
					-->
					<td>交易时间：<input type="text" class="date" readonly="true" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
					<li><a class="button" href="demo_page6.html" target="dialog"
						mask="true" title="查询框"><span>高级检索</span> </a>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<!-- 列表内容区域 -->
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="add_user.html" target="navTab"><span>添加</span>
			</a>
			</li>
			<li><a class="delete"
				href="userAction_delete.action?uid={sid_user}" target="ajaxTodo"
				title="确定要删除吗?"><span>删除</span> </a>
			</li>
			<li><a class="edit" href="demo_page4.html?uid={sid_user}"
				target="navTab"><span>修改</span> </a>
			</li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls"
				target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span>
			</a>
			</li>
			
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
			<!--  
				<th width="22" align="center"><input type="checkbox" group="ids"
					class="checkboxCtrl" ></th>
					-->
				<th width="40"  align="center">序号</th>
				<th width="150" align="center">订单号</th>
				<th width="120" align="center">交易号</th>
				<th width="80" align="center">交易金额</th>
				<th width="150" align="center">交易状态</th>
				<th width="120" align="center">交易时间</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator status="index" >
				<tr align=center valign=middle target="sid_user" rel="<s:property value="uid"/>">
					<!--
					<td><input name="ids" value='' <s:property value="uid"/>
						type="checkbox">
					</td>
  					-->
  				
					<td><s:property value="#index.index+1" /></td>
					<td><s:property value="OrderNO" /></td>
					<td><s:property value="TraceNO" /></td>
					<td><s:property value="TradeMoney" /> 元</td>
					<td><s:property value="m_sMessage" /></td>
					<td><s:date name="trade_time" format="yyyy-MM-dd HH:mm:ss" />
					
					</td>
				</tr>
			</s:iterator>


		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${fn:length(list)}条</span>
			
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${fn:length(list)}"
			numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
<script type="text/javascript" charset="utf-8">
	//每页显示数量
	$(".combox").change(function(){
		var value=$(".combox").find("option:selected").text(); ;
		alert(value);
	});
</script>