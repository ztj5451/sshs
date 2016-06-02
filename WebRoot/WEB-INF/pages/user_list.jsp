<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="pageHeader" style="height:25px;">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html"
		method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>我的客户：<input type="text" name="keyword" />
					</td>
					<td>创建时间：<input type="text" class="date" readonly="true" />
					</td>
				</tr>
			</table>
			<!--  
			<div class="subBar">
				<ul>
					<li>
					<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
					</div>
					</li>
					<li>
					<a class="button" href="demo_page6.html" target="dialog"
						mask="true" title="查询框"><span>高级检索</span> </a>
					</li>
				</ul>
			</div>
			-->
		</div>
	</form>
</div>
<!-- 列表内容区域 -->
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="user/add_user.html" target="navTab"><span>添加</span>
			</a></li>
			<li><a class="delete"
				href="userAction_delete.action?uid={sid_user}" target="ajaxTodo"
				title="确定要删除吗?"><span>删除</span> </a></li>
			<li><a class="edit" href="demo_page4.html?uid={sid_user}"
				target="navTab"><span>修改</span> </a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls"
				target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids"
					class="checkboxCtrl">
				</th>
				<th width="40" align="center">序号</th>
				<th width="120" align="center">登录名</th>
				<th width="120" align="center">用户名</th>
				<th width="140" align="center">邮箱</th>
				<th width="150" align="center">最后登录日期</th>
				<th width="150" align="center">最后登录IP</th>
				<th width="80" align="center">是否过期</th>
				<th width="80" align="center">是否锁定</th>
				<th width="80" align="center">是否可用</th>

			</tr>
		</thead>
		<tbody id="table">
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<!--  
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${totalCount}条</span>
			-->
		</div>
		<!-- 
		<div class="pagination" targetType="navTab" totalCount="200"
			numPerPage="20" pageNumShown="10" currentPage="1"></div>
 -->
	</div>
</div>
<script>
	var $table = $("#table");
	var loginUsers = ${requestScope.message};
	var message = '';
	for ( var i = 0; i < loginUsers.length; i++) {
		var object = loginUsers[i];
		var temp = '<tr  align=center valign=middle target="sid_user" ><td><input name="ids" value="'+object.id+'" type="checkbox" /></td><td>'
				+ (i + 1)
				+ '</td><td>'
				+ object.userName
				+ '</td><td>'
				+ object.name
				+ '</td><td>'
				+ object.email
				+ '</td><td>'
				+ object.loginDate
				+ '</td><td>'
				+ object.loginIp
				+ '</td><td>'
				+ object.userAccountNonExpired
				+ '</td><td>'
				+ object.userAccountNonLocked
				+ '</td><td>'
				+ object.userEnabled + '</td></tr>';
		message += temp;
		alert(message);
	}
	$table[0].innerHTML = message;
</script>
