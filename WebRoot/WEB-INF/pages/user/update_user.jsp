<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="../user/toAdd.action" target="navTab"
				disabled="true"><span>添加用户</span> </a>
			</li>
		</ul>
	</div>
	<form method="post" action="../user/userUpdate.action"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">

		<div class="pageFormContent" layoutH="84"
			style="width: 100%;text-align: center;float: left;margin-top: 20px">
			<!-- 第一列 -->
			<div style="width:45%;text-align: center;margin-left: 100px;">
				<input type="text" id="uid" name="id" style="display:none;">
				<p>
					<label>用户名:</label> <input name="username" id="username"
						type="text" size="30" class="required" alt="请输入用户账号" />
				</p>
				<p>
					<label>密 码:</label> <input name="password" type="text" size="30"
						class="required" alt="请输入用户密码" id="password" />
				</p>
				<p>
					<label>姓 名:</label> <input name="name" type="text" size="30"
						class="required" alt="请输入用户密码" id="name">
				</p>
				<p>
					<label>电子邮箱:</label> <input name="email" type="text" size="30"
						class="required" alt="请输入用户密码" id="email" />
				</p>

			</div>

		</div>


		<!-- 底部保存、取消按钮 -->
		<div class="formBar">

			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>



		</div>

	</form>
</div>
<script>
	var loginUser = ${requestScope.message};
	$("#uid").val(loginUser.id);
	$("#username").val(loginUser.userName);
	$("#password").val(loginUser.name);
	$("#email").val(loginUser.email);
</script>
